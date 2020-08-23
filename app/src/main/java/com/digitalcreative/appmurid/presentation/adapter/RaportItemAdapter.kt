package com.digitalcreative.appmurid.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Raport
import kotlinx.android.synthetic.main.item_raport_value.view.*

class RaportItemAdapter : RecyclerView.Adapter<RaportItemAdapter.ViewHolder>() {
    var raportItems = listOf<Raport.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_raport_value, parent, false)
        )
    }

    override fun getItemCount(): Int = raportItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val raportItem = raportItems[position]
        holder.bind(raportItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(raportItem: Raport.Item) {
            with(itemView) {
                tv_raport_text.text = raportItem.name
                tv_raport_value.text = raportItem.value
            }
        }
    }
}