package ru.dudar.findfilms.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres

interface TMDBGenres {
    @WorkerThread
    fun getTMDBGenresSync(): List<TheMovieGenres>

    fun getGenres(): LiveData<List<TheMovieGenres>>

    fun getTMDBGenresAsync(callback: (List<TheMovieGenres>) -> Unit)
}