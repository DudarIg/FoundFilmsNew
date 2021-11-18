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