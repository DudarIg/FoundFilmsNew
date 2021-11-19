package ru.dudar.findfilms.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres

interface TMDBGenres {
    @WorkerThread
    fun getTMDBGenresSync(): TheMovieGenres?

    fun getGenres(): LiveData<TheMovieGenres?>

}