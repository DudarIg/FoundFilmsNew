package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar.findfilms.R
import ru.dudar.findfilms.apiTheMovies.MainFilmsViewModel
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.FragmentMainFilmBinding
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.MyAdapter

class MainFilmsFragment : Fragment(R.layout.fragment_main_film) {

    private var _binding: FragmentMainFilmBinding? = null
    private val binding get() = _binding!!
    private var myAdapter = MyAdapter(mutableListOf<Film>(),2)
    val mainFilmsViewModel by viewModels<MainFilmsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       _binding = FragmentMainFilmBinding.bind(view)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.as_programm)

        binding.recyclerMainFilm.layoutManager = LinearLayoutManager(context)
        binding.recyclerMainFilm.adapter = myAdapter

        mainFilmsViewModel.listMainFilms.observe(this, Observer {
            it ?: return@Observer
            myAdapter.updateAdapter(it)
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() =  MainFilmsFragment()
    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.as_programm)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

