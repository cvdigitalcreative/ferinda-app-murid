package com.digitalcreative.appmurid.data.model

import com.google.gson.annotations.SerializedName

data class Agenda(
    val id: String,

    @SerializedName("kegiatan")
    val name: String,

    @SerializedName("tanggal")
    val date: String
)