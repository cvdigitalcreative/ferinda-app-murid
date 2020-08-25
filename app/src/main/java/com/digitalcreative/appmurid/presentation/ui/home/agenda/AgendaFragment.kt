package com.digitalcreative.appmurid.presentation.ui.home.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Agenda
import com.digitalcreative.appmurid.presentation.adapter.AgendaAdapter
import com.digitalcreative.appmurid.utils.helper.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_agenda.*


@AndroidEntryPoint
class AgendaFragment : Fragment() {

    private val viewModel by viewModels<AgendaViewModel>()
    private val loadingDialog by loadingDialog()
    private val agendaAdapter = AgendaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()

        rv_agenda.apply {
            adapter = agendaAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, this::showLoading)
        viewModel.agenda.observe(viewLifecycleOwner, this::showAgenda)
        viewModel.errorMessage.observe(viewLifecycleOwner, this::showErrorMessage)
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            if (!loadingDialog.isAdded) {
                loadingDialog.showDialog()
            }
        } else {
            if (loadingDialog.isAdded) {
                loadingDialog.closeDialog()
            }
        }
    }

    private fun showAgenda(agendas: List<Agenda>) {
        agendaAdapter.apply {
            this.agendas = agendas
            notifyDataSetChanged()
        }
    }

    private fun showErrorMessage(message: String) {
        Toasty.error(requireContext(), message, Toasty.LENGTH_LONG, true).show()
    }
}