package ru.dudar.findfilms

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentTime(): String {
        val date = Date()
        val formatter = SimpleDateFormat("dd-MM-yy kk:mm", Locale.getDefault())
        return formatter.format(date)
    }
}