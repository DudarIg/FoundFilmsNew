package ru.dudar.findfilms.data.api

import retrofit2.Call
import retrofit2.http.GET
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres

interface TheMoviesdbApi {
    @GET("genre/movie/list?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru")
    fun loadGanges(): Call<TheMovieGenres>
}