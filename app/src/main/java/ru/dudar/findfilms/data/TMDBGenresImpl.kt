package ru.dudar.findfilms.data

import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import ru.dudar.findfilms.domain.TheMovieGenres
import ru.dudar.findfilms.domain.Themoviedb
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TMDBGenresImpl : Themoviedb {
    private val theMovieDb = "https://api.themoviedb.org/3/genre/" +
            "movie/list?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru"
    private val gson by lazy { Gson() }

    override fun getTMDBGenresSync(): List<TheMovieGenres> {
        val result = emptyList<TheMovieGenres>().toMutableList()
        var connection: HttpURLConnection? = null
        try {
            val url = URL(theMovieDb)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000
            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
            val results = bufferedReader.readLines().toString()
            val resultArray = gson.fromJson(results, Array<TheMovieGenres>::class.java)

            resultArray.forEach {
                result.add(it)
            }
        } finally {
            connection?.disconnect()
        }
        return result
    }

    override fun getTMDBGenresAsync(callback: (List<TheMovieGenres>) -> Unit) {
        TODO("Not yet implemented")
    }

}