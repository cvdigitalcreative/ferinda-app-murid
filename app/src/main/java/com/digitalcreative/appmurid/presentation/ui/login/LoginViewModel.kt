package com.digitalcreative.appmurid.presentation.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.domain.usecases.common.Login
import com.digitalcreative.appmurid.utils.preferences.UserPreferences
import com.digitalcreative.appmurid.utils.helper.Constants
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class LoginViewModel @ViewModelInject constructor(
    private val useCase: Login,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mData = MutableLiveData<Boolean>()
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
                    preferences.apply {
                        setString(UserPreferences.KEY_NIS, response.data.id)
                        setString(UserPreferences.KEY_CLASS, response.data.classId)
                    }

                    mData.postValue(true)
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