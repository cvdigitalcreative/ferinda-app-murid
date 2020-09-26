package com.digitalcreative.appmurid.presentation.ui.home.report.semester

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.digitalcreative.appmurid.BuildConfig
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.data.model.Raport
import com.digitalcreative.appmurid.presentation.adapter.RaportAdapter
import com.digitalcreative.appmurid.presentation.ui.home.report.ReportViewModel
import com.digitalcreative.appmurid.utils.helper.loadingDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_semester.*

@AndroidEntryPoint
class SemesterFragment : Fragment() {

    private val viewModel by viewModels<ReportViewModel>()
    private val loadingDialog by loadingDialog()
    private val reportAdapter = RaportAdapter()

    companion object {
        const val TYPE_SEMESTER = "semester"
        const val SEMESTER_1 = "semester_1"
        const val SEMESTER_2 = "semester_2"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_semester, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val type = arguments?.getString(TYPE_SEMESTER) ?: SEMESTER_1
        if (type == SEMESTER_1) {
            viewModel.getFirstSemester()
        } else {
            viewModel.getSecondSemester()
        }

        initObservers()

        rv_raport.apply {
            adapter = reportAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner, this::showLoading)
        viewModel.data.observe(viewLifecycleOwner, this::showResponse)
        viewModel.errorMessage.observe(viewLifecycleOwner, this::showError)
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

    private fun showResponse(raport: Raport) {
        reportAdapter.apply {
            this.raports = raport.detail
            notifyDataSetChanged()
        }

        Glide.with(this)
            .load(BuildConfig.BASE_URL + raport.certificate)
            .into(img_certificate)

        tv_conclusion.text = raport.conclusion
    }

    private fun showError(message: String) {
        Toasty.error(requireContext(), message, Toasty.LENGTH_LONG, true).show()
    }
}