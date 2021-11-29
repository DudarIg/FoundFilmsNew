package ru.dudar.findfilms.data.apiTheMovies


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.dudar.findfilms.data.Themoviesgenres.TheMovieGenres
import ru.dudar.findfilms.data.films.Films

interface FilmApi {
    @GET("genre/movie/list?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru")
    fun loadGanres(): Call<TheMovieGenres>

    @GET("list/{ganre}?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru")
    fun loadFilmsGanre(@Path("ganre") ganre: Int ): Call<Films>
}
