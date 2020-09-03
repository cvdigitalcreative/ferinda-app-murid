package com.digitalcreative.appmurid.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Raport
import com.digitalcreative.appmurid.utils.animation.AnimUtil.TYPE_COLLAPSE
import com.digitalcreative.appmurid.utils.animation.AnimUtil.TYPE_EXPAND
import com.digitalcreative.appmurid.utils.animation.AnimUtil.animateCollapse
import com.digitalcreative.appmurid.utils.animation.AnimUtil.animateExpand
import com.digitalcreative.appmurid.utils.animation.AnimUtil.animateRotate
import com.digitalcreative.appmurid.utils.animation.AnimUtil.isExpanded
import kotlinx.android.synthetic.main.item_raport_section.view.*

class RaportAdapter : RecyclerView.Adapter<RaportAdapter.ViewHolder>() {
    var raports = listOf<Raport.Detail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_raport_section, parent, false)
        )
    }

    override fun getItemCount(): Int = raports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val raport = raports[position]
        holder.bind(raport)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(raport: Raport.Detail) {
            with(itemView) {
                tv_raport_section.text = raport.indicator

                val raportItemAdapter = RaportItemAdapter()
                raportItemAdapter.raportItems = raport.item
                rv_raport_section.apply {
                    adapter = raportItemAdapter
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                }

                card_section.setOnClickListener {
                    with(expand_section) expand@{
                        if (isExpanded(this)) {
                            animateCollapse(this)
                            animateRotate(this@with.arrow_section, TYPE_COLLAPSE)
                        } else {
                            animateExpand(this)
                            animateRotate(this@with.arrow_section, TYPE_EXPAND)
                        }
                    }
                }
            }
        }
    }
}