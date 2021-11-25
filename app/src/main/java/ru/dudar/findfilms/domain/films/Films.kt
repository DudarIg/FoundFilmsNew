package ru.dudar.findfilms.domain.films

import com.google.gson.annotations.SerializedName

data class Films (
    @SerializedName("created_by") var createdBy : String,
    @SerializedName("description") var description : String,
    @SerializedName("favorite_count") var favoriteCount : Int,
    @SerializedName("id") var id : String,
    @SerializedName("items") var items : List<Items>,
    @SerializedName("item_count") var itemCount : Int,
    @SerializedName("iso_639_1") var iso6391 : String,
    @SerializedName("name") var name : String,
    @SerializedName("poster_path") var posterPath : String

)

data class Items (
    @SerializedName("adult") var adult : Boolean,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("genre_ids") var genreIds : List<Int>,
    @SerializedName("id") var id : Int,
    @SerializedName("media_type") var mediaType : String,
    @SerializedName("original_language") var originalLanguage : String,
    @SerializedName("original_title") var originalTitle : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("release_date") var releaseDate : String,
    @SerializedName("title") var title : String,
    @SerializedName("video") var video : Boolean,
    @SerializedName("vote_average") var voteAverage : Double,
    @SerializedName("vote_count") var voteCount : Int

)
