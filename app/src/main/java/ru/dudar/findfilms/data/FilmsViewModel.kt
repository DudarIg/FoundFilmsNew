package ru.dudar.findfilms.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.R
import ru.dudar.findfilms.domain.TMDBGenres
import kotlin.random.Random

class FilmsViewModel : ViewModel() {
    val films_top = mutableListOf<Film>()
    val films_bottom = mutableListOf<Film>()
    private val films: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }
    private val films1: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }
    init {

        Thread {
            val films = films.getFilmsGanre(14)

            films!!.items.forEach {
                val film = Film()
                film.id = it.id
                film.photo = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${it.posterPath}"
                film.title = it.title
                film.year = it.releaseDate.substring(0,4)
                film.style = films.id
                films_top.add(film)
            }
            val films1 = films1.getFilmsGanre(35)

            films1!!.items.forEach {
                val film1 = Film()
                film1.id = it.id
                film1.photo = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${it.posterPath}"
                film1.title = it.title
                film1.year = it.releaseDate.substring(0,4)
                film1.style = films1.id
                films_bottom.add(film1)
            }
            //Thread.sleep(5_000)
        }.start()


    }
}