package ru.dudar.findfilms.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.dudar.findfilms.data.Film

@Database(entities = [Film::class], version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class FilmsDataBase: RoomDatabase() {
    abstract fun filmsDao(): FilmsDao

}