package ru.dudar.findfilms.apiBook

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dudar.findfilms.data.Ganr


class GanresViewModel: ViewModel() {

    val listGanresVM: LiveData<List<Ganr>>

    init {
        listGanresVM = ApiBookImpl().listGanres()
    }
}

//class FilmsViewModel: ViewModel() {
//    var listFilmsVM: LiveData<List<Film>>
//
//    init {
//        listFilmsVM = ApiBookImpl().filmsGange(ganre: Int)
//    }
//}




