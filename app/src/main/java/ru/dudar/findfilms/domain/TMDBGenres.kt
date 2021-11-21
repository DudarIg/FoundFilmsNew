package ru.dudar.findfilms.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres
import ru.dudar.findfilms.domain.films.Films

interface TMDBGenres {
    @WorkerThread
    fun getTMDBGenresSync(): TheMovieGenres?

    fun getGenres(): LiveData<TheMovieGenres?>

    fun getFilmsGanre(ganre: Int): Films?
}