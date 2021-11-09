package ru.dudar.findfilms.api

import retrofit2.Call
import retrofit2.http.GET

interface KinopoiskApi {

    @GET("/")
    fun fetchContents(): Call<String>

}