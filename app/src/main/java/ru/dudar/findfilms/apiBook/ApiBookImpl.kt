package ru.dudar.findfilms.apiBook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres
import ru.dudar.findfilms.domain.films.Films
private const val BASEURL = "https://api.themoviedb.org/3/"

class ApiBookImpl {

    private val api: FilmApi

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .build()
        api = retrofit.create(FilmApi::class.java)
    }

    fun listGanres(): LiveData<List<Ganr>> {
        val responseLiveData: MutableLiveData<List<Ganr>> = MutableLiveData()
        api.loadGanres().enqueue(object : Callback<TheMovieGenres> {
            override fun onResponse(
                call: Call<TheMovieGenres>,
                response: Response<TheMovieGenres>
            ) {
                val jsonGanrs: TheMovieGenres? = response.body()
                val ganrList = mutableListOf<Ganr>()
                jsonGanrs?.genres?.forEach {
                    val ganr = Ganr()
                    ganr.id = it.id
                    ganr.name = it.name
                    ganr.viv = false
                    ganrList.add(ganr)
                }
                responseLiveData.postValue(ganrList)
            }

            override fun onFailure(call: Call<TheMovieGenres>, t: Throwable) {
                TODO("Not yet implemented")
            }

        }
        )
        return responseLiveData
    }

    fun filmsGange(ganre: Int): LiveData<List<Film>> {
        val responseLiveData: MutableLiveData<List<Film>> = MutableLiveData()

        api.loadFilmsGanre(ganre).enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                val jsonFilms: Films? = response.body()
                val filmList = mutableListOf<Film>()

                jsonFilms?.items?.forEach {
                    val film = Film()
                    film.id = it.id
                    film.photo = "https://image.tmdb.org/t/p/w342${it.posterPath}"
                    film.title = it.title
                    film.year = it.releaseDate.substring(0, 4)
                    film.ganr = jsonFilms.id.toInt()
                    filmList.add(film)
                }
                responseLiveData.postValue(filmList)
            }

            override fun onFailure(call: Call<Films>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }
        )
        return responseLiveData
    }

}