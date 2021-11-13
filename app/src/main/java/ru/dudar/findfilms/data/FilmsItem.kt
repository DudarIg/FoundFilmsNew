package ru.dudar.findfilms.data

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class FilmItem(
    var filmId: String = "",
    @SerializedName("posterUrlPreview") var photo: String = "",
    @SerializedName("nameRu") var title: String = "",
    var year: String ="",
    var rating: String = ""
)
