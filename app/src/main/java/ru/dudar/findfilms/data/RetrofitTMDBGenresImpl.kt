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

private const val BASEDB = "https://api.themoviedb.org/3/"

class RetrofitTMDBGenresImpl: TMDBGenres {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: TheMoviesdbApi = retrofit.create(TheMoviesdbApi::class.java)


    override fun getTMDBGenresSync(): List<TheMovieGenres> {
        val result = api.loadGanges().execute().body() ?: emptyList()
        return result

    }

    override fun getGenres(): LiveData<List<TheMovieGenres>> {
        val liveData: MutableLiveData<List<TheMovieGenres>> = MutableLiveData()

        api.loadGanges().enqueue(object: Callback<List<TheMovieGenres>>{
            override fun onResponse(
                call: Call<List<TheMovieGenres>>,
                response: Response<List<TheMovieGenres>>
            ) {

                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<TheMovieGenres>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        } )

        return liveData


    }

    override fun getTMDBGenresAsync(callback: (List<TheMovieGenres>) -> Unit) {
        api.loadGanges().enqueue(object: Callback<List<TheMovieGenres>> {

            override fun onResponse(
                call: Call<List<TheMovieGenres>>,
                response: Response<List<TheMovieGenres>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<TheMovieGenres>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        } )
    }

}