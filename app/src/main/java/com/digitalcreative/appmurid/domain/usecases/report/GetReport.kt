package com.digitalcreative.appmurid.domain.usecases.report

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Raport
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetReport @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(studentId: String, semesterId: String): Result<Raport> {
        return networkRepository.getRaport(studentId, semesterId)
    }
}