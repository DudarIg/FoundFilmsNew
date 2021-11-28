package ru.dudar.findfilms.domain.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "films")
data class FilmsView(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "date") val date: Date = Date(),
    @ColumnInfo(name = "view") var isViews: Boolean = false
)

