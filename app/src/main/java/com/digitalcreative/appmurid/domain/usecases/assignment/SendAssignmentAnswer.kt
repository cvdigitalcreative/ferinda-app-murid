package com.digitalcreative.appmurid.domain.usecases.assignment

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class SendAssignmentAnswer @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(
        studentId: String,
        classId: String,
        assignmentId: String,
        questions: String,
        answers: String
    ): Result<String> {
        return networkRepository.sendAssignmentAnswer(
            studentId,
            classId,
            assignmentId,
            questions,
            answers
        )
    }
}