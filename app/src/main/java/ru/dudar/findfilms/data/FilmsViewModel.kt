package ru.dudar.findfilms.data

import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.R
import kotlin.random.Random

class FilmsViewModel : ViewModel() {
    val films_top = mutableListOf<Film>()
    val films_bottom = mutableListOf<Film>()

    init {

        val styles = listOf("Драма","Боевик","Фэнтези","Приключения","Комедия","Мелодрама")
        val photos  = listOf(R.drawable.afonya, R.drawable.angel, R.drawable.avatar_17, R.drawable.bogatir,
            R.drawable.br_ruka, R.drawable.ekipazh2, R.drawable.elki, R.drawable.garaj)
        val title = listOf("Афоня", "Ангел", "Аватар"," Богарырь", "Брилиановая рука",
            "Экипаж", "Елки", "Гараж")

        (0..7).forEach {
            val film = Film()
            film.photo = photos[it]
            film.title = title[it]
            film.year = Random.nextInt(41)+1980
            film.country = "Россия"
            film.style = styles.random()
            if ( it % 2 == 0) {
                films_top.add(film)
            } else {
                films_bottom.add(film)
            }
        }
    }
}