package com.digitalcreative.appmurid.domain.usecases.common

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import com.digitalcreative.appmurid.utils.Hash.sha256
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class LoginUseCase @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(emailNis: String, password: String): Result<Student> {
        val hashedPassword = sha256(password)
        return networkRepository.login(emailNis, hashedPassword)
    }
}