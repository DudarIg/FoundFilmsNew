package ru.dudar.findfilms.data.apiTheMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.domain.GanrOb
import ru.dudar.findfilms.data.repoDataBase.FilmsDbRepo


class GanresViewModel: ViewModel() {

    val listGanresVM: LiveData<List<Ganr>>
    init {
        listGanresVM = ApiBookImpl().listGanres()
    }
}

class FilmsViewModel: ViewModel() {

    var listFilmsTop: LiveData<List<Film>>?
    var listFilmsBottom: LiveData<List<Film>>?
    init {
        listFilmsTop = ApiBookImpl().filmsGange(GanrOb.ganrOb[0])
        listFilmsBottom = ApiBookImpl().filmsGange(GanrOb.ganrOb[1])
    }
}

class MainFilmsViewModel: ViewModel() {
    private val filmsDbRepo: FilmsDbRepo
    var listMainFilms: LiveData<List<Film>>
    init {
        filmsDbRepo = FilmsDbRepo.get()
        listMainFilms = filmsDbRepo.getFilms()
    }
}

class OneFilmViewModel: ViewModel() {
    private val filmsDbRepo: FilmsDbRepo
    var oneMainFilm: LiveData<Film?>
    init {
        filmsDbRepo = FilmsDbRepo.get()
        oneMainFilm = filmsDbRepo.getFilm(GanrOb.ganrOb[2])
    }
}





