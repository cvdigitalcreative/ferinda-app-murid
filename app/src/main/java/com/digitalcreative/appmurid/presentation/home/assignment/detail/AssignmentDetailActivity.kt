package com.digitalcreative.appmurid.presentation.home.assignment.detail

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.SectionAdapter
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.presentation.home.assignment.AssignmentViewModel
import com.digitalcreative.appmurid.utils.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_assignment_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AssignmentDetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<AssignmentViewModel>()
    private val loadingDialog by loadingDialog()
    private val sectionAdapter = SectionAdapter()

    private lateinit var assignment: Assignment
    private lateinit var manager: LinearLayoutManager
    private lateinit var assignmentQuestions: List<Assignment.Section>

    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_detail)
        setSupportActionBar(toolbar)

        assignment = intent.getParcelableExtra(EXTRA_ASSIGNMENT) ?: return

        initObservers()
        viewModel.getAssignmentQuestion(assignment.id)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = assignment.title
        }

        tv_assignment_description.text = assignment.description

        manager = LinearLayoutManager(this)

        btn_send_answer.setOnClickListener {
            val questions = assignmentQuestions
            val answers = getAnswers()

            viewModel.sendAssignmentAnswer(assignment.id, questions, answers)
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(this, Observer(this::showLoading))
        viewModel.assignmentQuestion.observe(this, Observer(this::showAssignmentQuestion))
        viewModel.successMessage.observe(this, Observer(this::showSuccessMessage))
        viewModel.errorMessage.observe(this, Observer(this::showErrorMessage))
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

    private fun showAssignmentQuestion(assignmentQuestions: List<Assignment.Section>) {
        this.assignmentQuestions = assignmentQuestions

        sectionAdapter.apply {
            sections = assignmentQuestions
            notifyDataSetChanged()
        }

        rv_assignment_question.apply {
            adapter = sectionAdapter
            layoutManager = manager
            setHasFixedSize(true)
        }
    }

    private fun showSuccessMessage(message: String) {
        Toasty.success(this, message, Toasty.LENGTH_LONG, true).show()
    }

    private fun showErrorMessage(message: String) {
        Toasty.error(this, message, Toasty.LENGTH_LONG, true).show()
    }

    private fun getAnswers(): List<String> {
        val listAnswer = mutableListOf<String>()

        lifecycleScope.launch {
            for ((index, _) in assignmentQuestions.withIndex()) {
                val container = manager.getChildAt(index)
                val rvQuestion = container?.findViewById<RecyclerView>(R.id.rv_question)

                val childManager = rvQuestion?.layoutManager
                val childCount = childManager?.itemCount ?: 0

                for (j in 0 until childCount) {
                    val childContainer = childManager?.getChildAt(j)
                    val radioGroup = childContainer?.findViewById<RadioGroup>(R.id.rg_choice)

                    radioGroup?.let {
                        val rbId = radioGroup.checkedRadioButtonId
                        val rb = childContainer.findViewById<RadioButton>(rbId)
                        val answerId = rb.tag as String

                        listAnswer.add(answerId)
                    }
                }
            }
        }

        return listAnswer
    }
}