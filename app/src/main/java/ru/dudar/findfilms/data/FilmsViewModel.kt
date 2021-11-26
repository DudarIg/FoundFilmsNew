package ru.dudar.findfilms.data


import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.domain.GanrOb
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.films.Films
import java.io.File
import java.security.AccessController.getContext


class FilmsViewModel : ViewModel() {
    var films_top = mutableListOf<Film>()
    var films_bottom = mutableListOf<Film>()
    var filmsJson: Films? = null
    private val films: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }


    fun getData()
    {
//        Thread {
//            ganrV = File("/GanrView.txt").readLines()
//        }.start()



        Thread {
            //Thread.sleep(1_000)

            filmsJson = films.getFilmsGanre(GanrOb.ganrOb[0])
            films_top = filmsToList(filmsJson!!)

            filmsJson = films.getFilmsGanre(GanrOb.ganrOb[1])
            films_bottom = filmsToList(filmsJson!!)

        }.start()
    }

    fun filmsToList(jsonFilms: Films): MutableList<Film> {
        val filmList = mutableListOf<Film>()
        jsonFilms.items.forEach {
            val film = Film()
            film.id = it.id
            film.photo = "https://image.tmdb.org/t/p/w342${it.posterPath}"
            film.title = it.title
            film.year = it.releaseDate.substring(0, 4)
            film.ganr = jsonFilms.id
            filmList.add(film)

        }
        return filmList
    }
}