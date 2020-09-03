package com.digitalcreative.appmurid.data.model

import com.google.gson.annotations.SerializedName

data class Raport(
    @SerializedName("id_rapot")
    val id: String,

    @SerializedName("kesimpulan")
    val conclusion: String,

    @SerializedName("foto_sertifikat")
    val certificate: String,

    @SerializedName("detail_rapot")
    val detail: List<Detail>
) {
    data class Detail(
        @SerializedName("indikator")
        val indicator: String,

        @SerializedName("detail_indikator")
        val item: List<Item>
    ) {
        data class Item(
            @SerializedName("id_detail_indikator")
            val id: String,

            @SerializedName("detail_indikator")
            val detail: String,

            @SerializedName("jawaban")
            val answer: Answer
        ) {
            data class Answer(
                @SerializedName("id_pilihan_jawaban_indikator")
                val id: String,

                @SerializedName("pilihan_jawaban")
                val answer: String
            )
        }
    }
}