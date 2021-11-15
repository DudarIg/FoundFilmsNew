package ru.dudar.findfilms.data

import com.google.gson.annotations.SerializedName


data class Example (

    @SerializedName("genres") var genres : ArrayList<Genres>

)