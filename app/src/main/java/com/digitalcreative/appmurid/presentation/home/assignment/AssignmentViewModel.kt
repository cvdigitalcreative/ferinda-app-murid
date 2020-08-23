package com.digitalcreative.appmurid.presentation.home.assignment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.domain.usecases.assignment.GetAllAssignment
import com.digitalcreative.appmurid.domain.usecases.assignment.GetAssignmentQuestion
import com.digitalcreative.appmurid.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class AssignmentViewModel @ViewModelInject constructor(
    private val getAllAssignmentUseCase: GetAllAssignment,
    private val getAssignmentQuestionUseCase: GetAssignmentQuestion,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mAssignment = MutableLiveData<List<Assignment>>()
    val assignment = mAssignment

    private val mAssignmentQuestion = MutableLiveData<List<Assignment.Section>>()
    val assignmentQuestion = mAssignmentQuestion

    private val mMessage = MutableLiveData<String>()
    val message = mMessage

    fun getAllAssignment() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            val classId = preferences.getString(UserPreferences.KEY_CLASS)

            when (val response = getAllAssignmentUseCase(studentId, classId)) {
                is Result.Success -> {
                    mAssignment.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mMessage.postValue(response.message)
                    mLoading.postValue(false)
                }
            }
        }
    }

    fun getAssignmentQuestion(assignmentId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            val classId = preferences.getString(UserPreferences.KEY_CLASS)

            when (val response = getAssignmentQuestionUseCase(studentId, classId, assignmentId)) {
                is Result.Success -> {
                    mAssignmentQuestion.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mMessage.postValue(response.message)
                    mLoading.postValue(false)
                }
            }
        }
    }
}