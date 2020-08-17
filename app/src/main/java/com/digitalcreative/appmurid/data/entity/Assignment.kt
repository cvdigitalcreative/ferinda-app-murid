package com.digitalcreative.appmurid.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Assignment(
    val title: String,
    val description: String,
    val sections: List<Section>
) : Parcelable {
    @Parcelize
    data class Section(
        val section: String,
        val questions: List<Question>
    ) : Parcelable {
        @Parcelize
        data class Question(
            val question: String,
            val choices: List<String>
        ) : Parcelable
    }
}
