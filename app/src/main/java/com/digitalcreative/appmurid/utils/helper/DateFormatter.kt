package com.digitalcreative.appmurid.utils.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun parseDateTime(dateTime: String): String {
        val locale = Locale("id", "ID")
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
        val formatter = SimpleDateFormat("dd MMMM yyyy", locale)

        return try {
            formatter.format(parser.parse(dateTime)!!)
        } catch (e: ParseException) {
            dateTime
        }
    }
}