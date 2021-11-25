package ru.dudar.findfilms.data

import java.io.Serializable


//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize


data class Film  (
    var id : Int = 0,
    var photo: String = "",
    var title: String = "",
    var year: String = "",
    var ganr: String = ""

): Serializable
