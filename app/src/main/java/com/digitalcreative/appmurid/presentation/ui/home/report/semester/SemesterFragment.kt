package com.digitalcreative.appmurid.presentation.ui.home.report.semester

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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

        val type = arguments?.getString(TYPE_SEMESTER)

        if (type == SEMESTER_1) {
            viewModel.getFirstSemester()
        } else {
            viewModel.getSecondSemester()
        }
        initObservers()

        val listRaport = listOf(
            Raport(
                "Bidang Pengembangan Nilai-Nilai Agama dan Moral",
                listOf(
                    Raport.Item(
                        "1. Menyayikan lagu indonesia raya",
                        "BM"
                    ),
                    Raport.Item(
                        "2. Menyayikan lagu USSR",
                        "BSB"
                    )
                ),
                null
            ),
            Raport(
                "Bidang Pengembangan Nilai-Nilai Agama dan Moral",
                listOf(
                    Raport.Item(
                        "1. Menyayikan lagu indonesia raya",
                        "BM"
                    ),
                    Raport.Item(
                        "2. Menyayikan lagu USSR",
                        "BSB"
                    )
                ),
                null
            ),
            Raport(
                "Bidang Pengembangan Nilai-Nilai Agama dan Moral",
                null,
                "Alhamdulillah ananda difa sudah dapat berhitung 1 - 20 dan sudah dapat mengucapkan abjad a - f"
            )
        )
        val raportAdapter = RaportAdapter()
        raportAdapter.raports = listRaport

        rv_raport.apply {
            adapter = raportAdapter
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

    }

    private fun showError(message: String) {
        Toasty.error(requireContext(), message, Toasty.LENGTH_LONG, true).show()
    }
}