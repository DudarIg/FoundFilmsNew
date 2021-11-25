package ru.dudar.findfilms.domain.Themoviesgenres

import com.google.gson.annotations.SerializedName
import ru.dudar.findfilms.domain.Themoviesgenres.Genres


data class TheMovieGenres (

    @SerializedName("genres") var genres : ArrayList<Genres>

)

data class Genres (

    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String

)