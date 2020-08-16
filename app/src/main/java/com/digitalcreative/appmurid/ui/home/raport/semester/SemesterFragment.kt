package com.digitalcreative.appmurid.ui.home.raport.semester

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.digitalcreative.appmurid.R


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

}