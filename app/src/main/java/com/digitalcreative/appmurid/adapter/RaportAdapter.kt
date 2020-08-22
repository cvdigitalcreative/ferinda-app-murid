package com.digitalcreative.appmurid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Raport
import com.digitalcreative.appmurid.utils.AnimUtil.TYPE_COLLAPSE
import com.digitalcreative.appmurid.utils.AnimUtil.TYPE_EXPAND
import com.digitalcreative.appmurid.utils.AnimUtil.animateCollapse
import com.digitalcreative.appmurid.utils.AnimUtil.animateExpand
import com.digitalcreative.appmurid.utils.AnimUtil.animateRotate
import com.digitalcreative.appmurid.utils.AnimUtil.isExpanded
import kotlinx.android.synthetic.main.item_raport_conclusion.view.*
import kotlinx.android.synthetic.main.item_raport_section.view.*
import kotlinx.android.synthetic.main.item_raport_section.view.tv_raport_section

class RaportAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var raports = listOf<Raport>()

    companion object {
        private const val TYPE_SECTION = 1
        private const val TYPE_CONCLUSION = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SECTION -> {
                SectionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_raport_section, parent, false)
                )
            }
            TYPE_CONCLUSION -> {
                ConclusionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_raport_conclusion, parent, false)
                )
            }
            else -> throw IllegalArgumentException("View Type not found!")
        }
    }

    override fun getItemCount(): Int = raports.size

    override fun getItemViewType(position: Int): Int {
        return if (raports[position].conclusion == null) {
            TYPE_SECTION
        } else {
            TYPE_CONCLUSION
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val raport = raports[position]
        when (holder) {
            is SectionViewHolder -> {
                holder.bind(raport)
            }

            is ConclusionViewHolder -> {
                holder.bind(raport)
            }
        }
    }

    class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(raport: Raport) {
            with(itemView) {
                tv_raport_section.text = raport.section

                val raportItemAdapter = RaportItemAdapter()
                raportItemAdapter.raportItems = raport.items!!

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

    class ConclusionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(raport: Raport) {
            with(itemView) {
                tv_raport_section.text = raport.section
                tv_raport_conclusion.text = raport.conclusion

                card_conclusion.setOnClickListener {
                    with(expand_conclusion) expand@{
                        if (isExpanded(this)) {
                            animateCollapse(this)
                            animateRotate(this@with.arrow_conclusion, TYPE_COLLAPSE)
                        } else {
                            animateExpand(this)
                            animateRotate(this@with.arrow_conclusion, TYPE_EXPAND)
                        }
                    }
                }
            }
        }
    }
}