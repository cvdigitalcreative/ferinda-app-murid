package com.digitalcreative.appmurid.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.digitalcreative.appmurid.utils.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped

@ActivityRetainedScoped
class HomeViewModel @ViewModelInject constructor(private val preferences: UserPreferences) :
    ViewModel() {

    fun logout() {
        preferences.apply {
            remove(UserPreferences.KEY_NIS)
            remove(UserPreferences.KEY_CLASS)
        }
    }
}