package com.digitalcreative.appmurid.presentation.ui.home.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.digitalcreative.appmurid.R
import kotlinx.android.synthetic.main.fragment_school.*


class SchoolFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listMisi = resources.getStringArray(R.array.deskripsi_misi)
        val misi = listMisi.joinToString(separator = "\n")

        tv_misi.text = misi
    }
}