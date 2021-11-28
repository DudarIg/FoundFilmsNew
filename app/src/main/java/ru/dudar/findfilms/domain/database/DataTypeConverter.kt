package ru.dudar.findfilms.domain.database

import androidx.room.TypeConverter
import java.util.*

class DataTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(milliSec: Long?): Date? {
        return milliSec?.let {
            Date(it)
        }
    }
}