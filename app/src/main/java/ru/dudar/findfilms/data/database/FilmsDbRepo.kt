package ru.dudar.findfilms.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.database.FilmsDataBase
import java.util.concurrent.Executors

private const val NAME_DATABASE = "films-database"

class FilmsDbRepo private constructor(context: Context){

    private val database: FilmsDataBase = Room.databaseBuilder(
        context.applicationContext,
        FilmsDataBase::class.java,
        NAME_DATABASE
    ).build()

    private val filmsDao = database.filmsDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getFilms(): LiveData<List<Film>> = filmsDao.getFilms()

    fun getFilm(id: Int): LiveData<Film?> = filmsDao.getFilm(id)

    fun addFilm(film: Film) {
        executor.execute {
            filmsDao.addFilm(film)
        }
    }
    fun deleteFilm(film: Film) {
        executor.execute {
            filmsDao.deleteFilm(film)
        }
    }

    companion object {
        private var INSTANCE: FilmsDbRepo? = null
        
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FilmsDbRepo(context)
            }
        }
        
        fun get(): FilmsDbRepo {
            return INSTANCE ?:
            throw IllegalStateException("База данных не инициализирована")
        }
    }
}