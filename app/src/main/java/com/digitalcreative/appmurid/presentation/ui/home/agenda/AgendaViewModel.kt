package com.digitalcreative.appmurid.presentation.ui.home.agenda

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Agenda
import com.digitalcreative.appmurid.domain.usecases.agenda.GetAllAgenda
import com.digitalcreative.appmurid.utils.helper.DateFormatter.parseDateTime
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class AgendaViewModel @ViewModelInject constructor(private val getAgendaUseCase: GetAllAgenda) :
    ViewModel() {
    private val mLoading = MutableLiveData<Boolean>()
    val loading = mLoading

    private val mAgenda = MutableLiveData<List<Agenda>>()
    val agenda = mAgenda

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage = mErrorMessage

    init {
        getAllAgenda()
    }

    private fun getAllAgenda() {
        viewModelScope.launch(Dispatchers.IO) {
            mLoading.postValue(true)

            when (val response = getAgendaUseCase()) {
                is Result.Success -> {
                    val listAgenda = response.data.map {
                        it.copy(date = parseDateTime(it.date))
                    }

                    mAgenda.postValue(listAgenda)
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