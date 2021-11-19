package ru.dudar.findfilms.data

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.films.Films
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class TMDBGenresImpl : TMDBGenres {
    private val theMovieDb = "https://api.themoviedb.org/3/genre/" +
            "movie/list?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru"
    private val gson by lazy { Gson() }

    override fun getTMDBGenresSync(): TheMovieGenres? {
        var result: TheMovieGenres? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(theMovieDb)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000
            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
            val results = bufferedReader.readLines().toString()
            result = gson.fromJson(results, TheMovieGenres::class.java)

        } catch (ex: Exception) {
                        result = null
        } finally {
            connection?.disconnect()
        }
        return result
    }

    override fun getGenres(): LiveData<TheMovieGenres?> {
        TODO("Not yet implemented")
    }

    override fun getFilmsGanre(ganre: Int): Films {
        TODO("Not yet implemented")
    }
}