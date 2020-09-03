package com.digitalcreative.appmurid.presentation.ui.home.assignment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.domain.usecases.assignment.GetAllAssignment
import com.digitalcreative.appmurid.domain.usecases.assignment.GetAssignmentQuestion
import com.digitalcreative.appmurid.domain.usecases.assignment.SendAssignmentAnswer
import com.digitalcreative.appmurid.utils.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class AssignmentViewModel @ViewModelInject constructor(
    private val getAssignmentUseCase: GetAllAssignment,
    private val getQuestionUseCase: GetAssignmentQuestion,
    private val sendAssignmentUseCase: SendAssignmentAnswer,
    private val preferences: UserPreferences
) : ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mAssignment = MutableLiveData<List<Assignment>>()
    val assignment = mAssignment

    private val mAssignmentQuestion = MutableLiveData<List<Assignment.Section>>()
    val assignmentQuestion = mAssignmentQuestion

    private val mSuccessMessage = MutableLiveData<String>()
    val successMessage = mSuccessMessage

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage

    fun getAllAssignment() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            val classId = preferences.getString(UserPreferences.KEY_CLASS)

            when (val response = getAssignmentUseCase(studentId, classId)) {
                is Result.Success -> {
                    mAssignment.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mErrorMessage.postValue(response.message)
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

            when (val response = getQuestionUseCase(studentId, classId, assignmentId)) {
                is Result.Success -> {
                    mAssignmentQuestion.postValue((response.data))
                    mLoading.postValue(false)
                }

                is Result.ErrorRequest -> {
                    mErrorMessage.postValue(response.message)
                    mLoading.postValue(false)
                }
            }
        }
    }

    fun sendAssignmentAnswer(
        assignmentId: String,
        questions: List<Assignment.Section>,
        answers: List<String>
    ) {
        viewModelScope.launch {
            mLoading.postValue(true)

            val listQuestionId = mutableListOf<String>()
            questions.map { section ->
                section.questions.forEach { question ->
                    listQuestionId.add(question.id)
                }
            }

            val studentId = preferences.getString(UserPreferences.KEY_NIS)
            val classId = preferences.getString(UserPreferences.KEY_CLASS)
            val questionFlatten = listQuestionId.joinToString(prefix = "[", postfix = "]")
            val answersFlatten = answers.joinToString(prefix = "[", postfix = "]")

            launch(Dispatchers.IO) {
                val response = sendAssignmentUseCase(
                    studentId,
                    classId,
                    assignmentId,
                    questionFlatten,
                    answersFlatten
                )

                when (response) {
                    is Result.Success -> {
                        mSuccessMessage.postValue((response.data))
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
}