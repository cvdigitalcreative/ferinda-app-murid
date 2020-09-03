package com.digitalcreative.appmurid.domain.usecases.assignment

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetAllAssignment @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(studentId: String, classId: String): Result<List<Assignment>> {
        return networkRepository.getAllAssignment(studentId, classId)
    }
}