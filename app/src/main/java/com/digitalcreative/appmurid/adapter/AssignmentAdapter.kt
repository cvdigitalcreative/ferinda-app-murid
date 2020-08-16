package com.digitalcreative.appmurid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.entity.Assignment
import kotlinx.android.synthetic.main.item_tugas.view.*

class AssignmentAdapter : RecyclerView.Adapter<AssignmentAdapter.ViewHolder>() {
    var assignments = listOf<Assignment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tugas, parent, false)
        )
    }

    override fun getItemCount(): Int = assignments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[position]
        holder.bind(assignment)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(assignment: Assignment) {
            with(itemView) {
                tv_assignment_title.text = assignment.title
                tv_assignment_description.text = assignment.description
            }
        }
    }
}