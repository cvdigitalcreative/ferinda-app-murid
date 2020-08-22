package com.digitalcreative.appmurid.presentation.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.domain.usecases.common.LoginUseCase
import com.digitalcreative.appmurid.preferences.UserPreferences
import com.digitalcreative.appmurid.utils.Constants
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class LoginViewModel @ViewModelInject constructor(
    private val useCase: LoginUseCase,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mData = MutableLiveData<Student>()
    val data = mData

    private val mMessage = MutableLiveData<String>()
    val message = mMessage

    fun isUserLoggedIn(): Boolean {
        return preferences.getString(UserPreferences.KEY_NIS).isNotEmpty()
    }

    fun login(emailNis: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (emailNis.isBlank() || password.isBlank()) {
                mMessage.postValue(Constants.EMPTY_INPUT_ERROR)
                return@launch
            }

            mLoading.postValue(true)
            when (val response = useCase(emailNis, password)) {
                is Result.Success -> {
                    preferences.setString(UserPreferences.KEY_NIS, response.data.id)

                    mData.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mMessage.postValue(response.message)
                    mLoading.postValue(false)
                }

                is Result.ErrorInput -> {
                    mMessage.postValue(response.message)
                }
            }
        }
    }
}