package ru.dudar.findfilms.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.Serializable

data class Film  (
    var photo: Int = 0,
    var title: String = "",
    var year: Int = 0,
    var country: String = "",
    var style: String = ""

): Serializable
