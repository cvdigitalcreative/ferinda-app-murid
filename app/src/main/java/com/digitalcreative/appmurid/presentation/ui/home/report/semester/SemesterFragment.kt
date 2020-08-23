package com.digitalcreative.appmurid.presentation.ui.home.report.semester

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.presentation.adapter.RaportAdapter
import com.digitalcreative.appmurid.data.model.Raport
import kotlinx.android.synthetic.main.fragment_semester.*


class SemesterFragment : Fragment() {

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
}