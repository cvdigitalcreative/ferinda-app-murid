package com.digitalcreative.appmurid.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun FragmentActivity.loadingDialog(): Lazy<LoadingDialog> {
    return lazy {
        LoadingDialog(supportFragmentManager)
    }
}

fun Fragment.loadingDialog(): Lazy<LoadingDialog> {
    return lazy {
        LoadingDialog(childFragmentManager)
    }
}