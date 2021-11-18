package ru.dudar.findfilms.domain

import com.google.gson.annotations.SerializedName


data class TheMovieGenres (

    @SerializedName("genres") var genres : ArrayList<Genres>

)