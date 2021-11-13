package ru.dudar.findfilms.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface KinopoiskApi {


    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS" +
            "&page=1")
    @Headers("accept:application/json", "X-API-KEY:312a18ad-dbc2-40bc-8ea5-3a1c4abc40f2")
    fun popularFilms(): Call<String>

}