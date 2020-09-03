package com.digitalcreative.appmurid.domain.usecases.profile

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Profile
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetDetailProfile @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(studentId: String): Result<Profile> {
        return networkRepository.getDetailProfile(studentId)
    }
}