package com.digitalcreative.appmurid.ui.home.tugas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalcreative.appmurid.R
import com.digitalcreative.appmurid.adapter.AssignmentAdapter
import com.digitalcreative.appmurid.data.entity.Assignment
import kotlinx.android.synthetic.main.fragment_tugas.*


class TugasFragment : Fragment() {

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
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
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
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
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
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
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
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
                                )
                            ),
                            Assignment.Section.Question(
                                "Soal 2",
                                listOf(
                                    Assignment.Section.Question.Choice("A"),
                                    Assignment.Section.Question.Choice("B"),
                                    Assignment.Section.Question.Choice("C")
                                )
                            )
                        )
                    )
                )
            )
        )

        val assignmentAdapter = AssignmentAdapter().apply { assignments = listAssignment }
        rv_tugas.apply {
            adapter = assignmentAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}