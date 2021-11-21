package ru.dudar.findfilms.data


import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.films.Films

class FilmsViewModel : ViewModel() {
    var films_top = mutableListOf<Film>()
    var films_bottom = mutableListOf<Film>()
    var filmsJson: Films? = null
    private val films: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }
    var ganr : List<String> = listOf("14", "35")

    init {
        Thread {
            filmsJson = films.getFilmsGanre(ganr[0].toInt())
            films_top = filmsToList(filmsJson!!)

            filmsJson = films.getFilmsGanre(ganr[1].toInt())
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