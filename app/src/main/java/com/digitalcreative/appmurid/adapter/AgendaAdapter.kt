package com.digitalcreative.appmurid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Agenda
import kotlinx.android.synthetic.main.item_agenda.view.*

class AgendaAdapter : RecyclerView.Adapter<AgendaAdapter.ViewHolder>() {
    var agendas = listOf<Agenda>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agenda, parent, false)
        )
    }

    override fun getItemCount(): Int = agendas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agenda = agendas[position]
        holder.bind(agenda)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(agenda: Agenda) {
            with(itemView) {
                tv_agenda_title.text = agenda.name
                tv_agenda_date.text = agenda.date
            }
        }
    }
}