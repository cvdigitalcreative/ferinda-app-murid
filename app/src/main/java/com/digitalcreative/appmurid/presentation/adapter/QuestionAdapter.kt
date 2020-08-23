package com.digitalcreative.appmurid.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.utils.helper.visible
import kotlinx.android.synthetic.main.item_assignment_question.view.*

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    var questions = listOf<Assignment.Section.Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_assignment_question, parent, false)
        )
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Assignment.Section.Question) {
            with(itemView) {
                tv_question.text = question.question

                for ((index, value) in question.choices.withIndex()) {
                    val btn = RadioButton(context).apply {
                        id = View.generateViewId()
                        text = value.choice
                        tag = value.id
                        isChecked = index == 0
                    }
                    rg_choice.addView(btn)
                }

                rg_choice.visible()
            }
        }
    }
}