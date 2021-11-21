package ru.dudar.findfilms.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dudar.findfilms.data.api.TheMoviesdbApi
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres
import ru.dudar.findfilms.domain.films.Films

private const val BASEDB = "https://api.themoviedb.org/3/"

class RetrofitTMDBGenresImpl : TMDBGenres {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: TheMoviesdbApi = retrofit.create(TheMoviesdbApi::class.java)

    override fun getTMDBGenresSync(): TheMovieGenres? {
        val result = api.loadGanges().execute().body() ?: null
        return result
    }

    override fun getGenres(): LiveData<TheMovieGenres?> {
        val liveData: MutableLiveData<TheMovieGenres?> = MutableLiveData()
        api.loadGanges().enqueue(object : Callback<TheMovieGenres?> {
            override fun onResponse(
                call: Call<TheMovieGenres?>,
                response: Response<TheMovieGenres?>
            ) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<TheMovieGenres?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return liveData
    }

    override fun getFilmsGanre(ganre: Int): Films? {
        val result = api.loadFilmsGanre(ganre).execute().body()
        return result
    }

}