package com.digitalcreative.appmurid.data.entity

data class Raport(
    val section: String,
    val items: List<Item>?,
    val conclusion: String?
) {
    data class Item(
        val name: String,
        val value: String
    )
}