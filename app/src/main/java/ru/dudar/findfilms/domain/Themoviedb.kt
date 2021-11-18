package ru.dudar.findfilms.domain

import androidx.annotation.WorkerThread

interface Themoviedb {
    @WorkerThread
    fun getTMDBGenresSync(): List<TheMovieGenres>

    fun getTMDBGenresAsync(callback: (List<TheMovieGenres>) -> Unit)
}