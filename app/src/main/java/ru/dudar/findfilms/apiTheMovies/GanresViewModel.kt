package ru.dudar.findfilms.apiTheMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.data.Ganr


class GanresViewModel: ViewModel() {

    val listGanresVM: LiveData<List<Ganr>>

    init {
        listGanresVM = ApiBookImpl().listGanres()
    }
}

//class FilmsViewModelTop: ViewModel() {
//    var listFilmsVM: LiveData<List<Film>>
//
//    init {
//        val ganre = GanrOb.ganrOb[0]
//        listFilmsVM = ApiBookImpl().filmsGange(ganre)
//    }
//}
//class FilmsViewModelBottom: ViewModel() {
//    var listFilmsVM: LiveData<List<Film>>
//
//    init {
//        val ganre = GanrOb.ganrOb[1]
//        listFilmsVM = ApiBookImpl().filmsGange(ganre)
//    }
//}





