package ru.dudar.findfilms.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.R
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.films.Films
import kotlin.random.Random

class FilmsViewModel : ViewModel() {
    var films_top = mutableListOf<Film>()
    var films_bottom = mutableListOf<Film>()
    var filmsJson: Films? = null
    private val films: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }

    init {
        Thread {
            filmsJson = films.getFilmsGanre(14)
            films_top = filmsToList(filmsJson!!)

            filmsJson = films.getFilmsGanre(35)
            films_bottom = filmsToList(filmsJson!!)

        }.start()
    }

    fun filmsToList(jsonFilms: Films): MutableList<Film> {
        val filmList = mutableListOf<Film>()
        jsonFilms.items.forEach {
            val film = Film()
            film.id = it.id
            film.photo = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${it.posterPath}"
            film.title = it.title
            film.year = it.releaseDate.substring(0, 4)
            film.style = jsonFilms.id
            filmList.add(film)
        }
        return filmList
    }
}