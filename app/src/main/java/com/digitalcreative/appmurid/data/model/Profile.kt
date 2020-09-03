package com.digitalcreative.appmurid.data.model

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("nis")
    val id: String,

    @SerializedName("nama")
    val name: String,

    val email: String,

    @SerializedName("nip")
    val teacherId: String,

    @SerializedName("nama_guru")
    val teacherName: String,

    @SerializedName("email_guru")
    val teacherEmail: String,

    @SerializedName("teman_kelas")
    val friends: List<Friend>
) {
    data class Friend(
        @SerializedName("nis")
        val id: String,

        @SerializedName("nama")
        val name: String,

        val email: String
    )
}