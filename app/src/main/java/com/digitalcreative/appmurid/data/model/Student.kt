package com.digitalcreative.appmurid.data.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("nis")
    val id: String,

    @SerializedName("nama")
    val name: String,

    val email: String,

    @SerializedName("jenis_kelamin")
    val gender: String,

    @SerializedName("tempat_lahir")
    val birthplace: String,

    @SerializedName("tanggal_lahir")
    val birthday: String,

    @SerializedName("agama")
    val religion: String,

    @SerializedName("alamat")
    val address: String,

    @SerializedName("telepon")
    val phone: String,

    @SerializedName("id_kelas")
    val classId: String
)