package com.digitalcreative.appmurid.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Assignment
import kotlinx.android.synthetic.main.item_assignment_section.view.*

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {
    var sections = listOf<Assignment.Section>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_assignment_section, parent, false)
        )
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(section: Assignment.Section) {
            with(itemView) {
                tv_section.text = section.section

                val questionAdapter = QuestionAdapter()
                questionAdapter.questions = section.questions

                rv_question.apply {
                    adapter = questionAdapter
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                }
            }
        }
    }
}