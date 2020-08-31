package com.digitalcreative.appmurid.presentation.ui.home.report

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Raport
import com.digitalcreative.appmurid.domain.usecases.report.GetReport
import com.digitalcreative.appmurid.utils.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class ReportViewModel @ViewModelInject constructor(
    private val getReportUseCase: GetReport,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mData = MutableLiveData<Raport>()
    val data = mData

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage

    fun getFirstSemester() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            when (val response = getReportUseCase(studentId, "1")) {
                is Result.Success -> {
                    mData.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mErrorMessage.postValue(response.message)
                    mLoading.postValue(false)
                }
            }
        }
    }

    fun getSecondSemester() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            when (val response = getReportUseCase(studentId, "2")) {
                is Result.Success -> {
                    mData.postValue((response.data))
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