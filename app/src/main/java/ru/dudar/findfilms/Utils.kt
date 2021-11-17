package ru.dudar.findfilms

import android.content.Context
import ru.dudar.findfilms.data.Film
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getCurrentTime(): String {
        val date = Date()
        val formatter = SimpleDateFormat("dd-MM-yy kk:mm", Locale.getDefault())
        return formatter.format(date)
    }

    fun saveFilm(context: Context, film: Film) {
        val path = context.getFilesDir()
        val file = File(path, "FilmsView.txt")
        file.appendText("${film!!.style} ${film!!.title}\n")
    }
}