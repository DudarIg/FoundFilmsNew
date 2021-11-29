package ru.dudar.findfilms.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.dudar.findfilms.data.Film

@Dao
interface FilmsDao {
    @Query("SELECT * FROM filmsviews")
    fun getFilms(): LiveData<List<Film>>

    @Query("SELECT * FROM filmsviews WHERE id=(:id)")
    fun getFilm(id: Int): LiveData<Film?>

    @Insert
    fun addFilm(film: Film)

    @Delete
    fun deleteFilm(film: Film)
}