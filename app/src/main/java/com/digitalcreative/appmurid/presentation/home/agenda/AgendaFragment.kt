package com.digitalcreative.appmurid.presentation.home.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.AgendaAdapter
import com.digitalcreative.appmurid.data.model.Agenda
import kotlinx.android.synthetic.main.fragment_agenda.*


class AgendaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agenda, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listAgenda = listOf(
            Agenda(
                "Agenda 1",
                "Senin, 1 Agustus 2020"
            ),
            Agenda(
                "Agenda 2",
                "Selasa, 2 Agustus 2020"
            ),
            Agenda(
                "Agenda 3",
                "Rabu, 3 Agustus 2020"
            ),
            Agenda(
                "Agenda 4",
                "Kamis, 14 Agustus 2020"
            )
        )

        val agendaAdapter = AgendaAdapter()
        agendaAdapter.agendas = listAgenda

        rv_agenda.apply {
            adapter = agendaAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}