package com.digitalcreative.appmurid.utils.animation

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import androidx.constraintlayout.widget.ConstraintLayout
import com.digitalcreative.appmurid.utils.helper.gone
import com.digitalcreative.appmurid.utils.helper.visible

object AnimUtil {
    const val TYPE_EXPAND = 1
    const val TYPE_COLLAPSE = 2

    fun isExpanded(view: View): Boolean = view.visibility == View.VISIBLE

    fun animateExpand(view: View) {
        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            (view.parent as View).width,
            View.MeasureSpec.EXACTLY
        )
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)

        val targetHeight = view.measuredHeight

        view.layoutParams.height = 1
        view.visible()

        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation?
            ) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f)
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    else
                        (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (targetHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
    }

    fun animateCollapse(view: View) {
        val initialHeight = view.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                if (interpolatedTime == 1f) {
                    view.gone()
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (initialHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(a)
    }

    fun animateRotate(view: View, type: Int) {
        val anim = when (type) {
            TYPE_EXPAND -> RotateAnimation(
                0F,
                90F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            TYPE_COLLAPSE -> RotateAnimation(
                90F,
                0F,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            else -> throw IllegalArgumentException("Type not found!")
        }.apply {
            duration = 150
            fillAfter = true
        }

        view.animation = anim
    }
}