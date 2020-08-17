package com.digitalcreative.appmurid.ui.home.tugas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.AssignmentAdapter
import com.digitalcreative.appmurid.data.entity.Assignment
import com.digitalcreative.appmurid.ui.home.tugas.detail.DetailTugasActivity
import kotlinx.android.synthetic.main.fragment_tugas.*


class TugasFragment : Fragment(), AssignmentAdapter.ClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tugas, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listAssignment = listOf(
            Assignment(
                "Melukis",
                "Melukis merupakan sebuah tugas seni budaya",
                listOf(
                    Assignment.Section(
                        "A. Perkembangan 1",
                        listOf(
                            Assignment.Section.Question(
                                "Soal 1",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            )
                        )
                    ),
                    Assignment.Section(
                        "B. Perkembangan 2",
                        listOf(
                            Assignment.Section.Question(
                                "Soal 1",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            )
                        )
                    )
                )
            ),
            Assignment(
                "Melukis 2",
                "Melukis merupakan sebuah tugas seni budaya",
                listOf(
                    Assignment.Section(
                        "A. Perkembangan 1",
                        listOf(
                            Assignment.Section.Question(
                                "Soal 1",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            )
                        )
                    ),
                    Assignment.Section(
                        "B. Perkembangan 2",
                        listOf(
                            Assignment.Section.Question(
                                "Soal 1",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    "A",
                                    "B",
                                    "C"
                                )
                            )
                        )
                    )
                )
            )
        )

        val assignmentAdapter = AssignmentAdapter().apply {
            assignments = listAssignment
            listener = this@TugasFragment
        }
        rv_tugas.apply {
            adapter = assignmentAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onItemClicked(assignment: Assignment) {
        val intent = Intent(requireContext(), DetailTugasActivity::class.java).apply {
            putExtra(DetailTugasActivity.EXTRA_ASSIGNMENT, assignment)
        }
        startActivity(intent)
    }
}