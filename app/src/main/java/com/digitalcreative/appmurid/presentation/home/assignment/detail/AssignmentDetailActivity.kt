package com.digitalcreative.appmurid.presentation.home.assignment.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.SectionAdapter
import com.digitalcreative.appmurid.data.model.Assignment
import kotlinx.android.synthetic.main.activity_assignment_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class AssignmentDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_detail)
        setSupportActionBar(toolbar)

        val assignment = intent.getParcelableExtra<Assignment>(EXTRA_ASSIGNMENT) ?: return

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = assignment.title
        }

        tv_assignment_description.text = assignment.description

        val sectionAdapter = SectionAdapter()
//        sectionAdapter.sections = assignment.sections

        val manager = LinearLayoutManager(this)

        rv_assignment_question.apply {
            adapter = sectionAdapter
            layoutManager = manager
            setHasFixedSize(true)
        }

        btn_send_answer.setOnClickListener {
//            for ((index, _) in assignment.sections.withIndex()) {
//                val container = manager.getChildAt(index)
//                val rvQuestion = container?.findViewById<RecyclerView>(R.id.rv_question)
//
//                val childManager = rvQuestion?.layoutManager
//                val childCount = childManager?.itemCount ?: 0
//
//                for (j in 0 until childCount) {
//                    val childContainer = childManager?.getChildAt(j)
//                    val radioGroup = childContainer?.findViewById<RadioGroup>(R.id.rg_choice)
//
//                    radioGroup?.let {
//                        val rbId = radioGroup.checkedRadioButtonId
//                        val rb = childContainer.findViewById<RadioButton>(rbId)
//                        val rbIndex = radioGroup.indexOfChild(rb)
//                        val rbText = rb.text
//                        val rbTag = rb.tag
//
//                        Log.e("A", "Index = $rbIndex")
//                        Log.e("A", "Text = $rbText")
//                        Log.e("A", "Tag = $rbTag")
//                    }
//                }
//            }
        }
    }
}