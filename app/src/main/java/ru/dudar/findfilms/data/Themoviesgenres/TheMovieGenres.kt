package ru.dudar.findfilms.data.Themoviesgenres

import com.google.gson.annotations.SerializedName

data class TheMovieGenres (
    @SerializedName("genres") var genres : ArrayList<Genres>
)

data class Genres (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
)