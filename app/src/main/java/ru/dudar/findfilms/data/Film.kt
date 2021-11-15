package ru.dudar.findfilms.data

import java.io.Serializable


//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize


data class Film  (
    var photo: Int = 0,
    var title: String = "",
    var year: Int = 0,
    var country: String = "",
    var style: String = ""

): Serializable
