package ru.dudar.findfilms.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "filmsviews")
data class Film  (
    @PrimaryKey
    @ColumnInfo(name = "id") var id : Int = 0,
    @ColumnInfo(name = "photo")var photo: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "year") var year: String = "",
    @ColumnInfo(name = "ganr") var ganr: Int = 0

): Serializable
