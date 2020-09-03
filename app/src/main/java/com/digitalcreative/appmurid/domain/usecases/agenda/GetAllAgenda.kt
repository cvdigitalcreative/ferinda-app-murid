package com.digitalcreative.appmurid.domain.usecases.agenda

import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Agenda
import com.digitalcreative.appmurid.data.repository.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GetAllAgenda @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(): Result<List<Agenda>> {
        return networkRepository.getAllAgenda()
    }
}