package com.digitalcreative.appmurid.presentation.ui.home.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Profile
import com.digitalcreative.appmurid.domain.usecases.profile.GetDetailProfile
import com.digitalcreative.appmurid.utils.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class ProfileViewModel @ViewModelInject constructor(
    private val getProfileUseCase: GetDetailProfile,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mProfile = MutableLiveData<Profile>()
    val profile = mProfile

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage

    init {
        getDetailProfile()
    }

    private fun getDetailProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            when (val response = getProfileUseCase(studentId)) {
                is Result.Success -> {
                    val parsedData = response.data.copy(
                        name = response.data.name.replace(" ", "+"),
                        teacherName = response.data.teacherName.replace(" ", "+"),
                        friends = response.data.friends.map { item ->
                            item.copy(name = item.name.replace(" ", "+"))
                        }
                    )
                    mProfile.postValue(parsedData)
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mErrorMessage.postValue(response.message)
                    mLoading.postValue(false)
                }
            }
        }
    }
}