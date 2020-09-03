package com.digitalcreative.appmurid.presentation.ui.home.assignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.presentation.adapter.AssignmentAdapter
import com.digitalcreative.appmurid.presentation.ui.home.assignment.detail.AssignmentDetailActivity
import com.digitalcreative.appmurid.utils.helper.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_assignment.*

@AndroidEntryPoint
class AssignmentFragment : Fragment(), AssignmentAdapter.ClickListener {

    private val viewModel by viewModels<AssignmentViewModel>()
    private val loadingDialog by loadingDialog()
    private val assignmentAdapter = AssignmentAdapter()
    private val assignmentDetailResults = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        this::handleResult
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assignment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initObservers()
        viewModel.getAllAssignment()

        assignmentAdapter.listener = this

        rv_tugas.apply {
            adapter = assignmentAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onItemClicked(assignment: Assignment) {
        val intent = Intent(requireContext(), AssignmentDetailActivity::class.java).apply {
            putExtra(AssignmentDetailActivity.EXTRA_ASSIGNMENT, assignment)
        }
        assignmentDetailResults.launch(intent)
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == AssignmentDetailActivity.RESULT_SUCCESS) {
            viewModel.getAllAssignment()
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, Observer(this::showLoading))
        viewModel.assignment.observe(viewLifecycleOwner, Observer(this::showAssignments))
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer(this::showMessage))
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

    private fun showAssignments(assignments: List<Assignment>) {
        assignmentAdapter.apply {
            this.assignments = assignments
            notifyDataSetChanged()
        }
    }

    private fun showMessage(message: String) {
        Toasty.error(requireContext(), message, Toasty.LENGTH_LONG, true).show()
    }
}