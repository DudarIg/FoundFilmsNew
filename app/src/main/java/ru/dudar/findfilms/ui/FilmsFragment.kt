package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.ContextUtils
import ru.dudar.findfilms.R
import ru.dudar.findfilms.apiTheMovies.ApiBookImpl
import ru.dudar.findfilms.apiTheMovies.FilmsViewModel
import ru.dudar.findfilms.apiTheMovies.GanresViewModel
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.FilmsFragmentBinding
import ru.dudar.findfilms.domain.GanrOb
import ru.dudar.findfilms.domain.MyAdapter


class FilmsFragment : Fragment(R.layout.films_fragment) {

    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!
    private var myAdapter0 = MyAdapter(mutableListOf<Film>(),1)
    private var myAdapter1 = MyAdapter(mutableListOf<Film>(),1)
    //val filmsViewModel by viewModels<FilmsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FilmsFragmentBinding.bind(view)


        binding.recyclerTop.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerTop.adapter = myAdapter0
        binding.recyclerBottom?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerBottom?.adapter = myAdapter1

        recyclers_init()
    }

    private fun recyclers_init() {
       getViewModelStore().clear()
       val filmsViewModel by viewModels<FilmsViewModel>()

        filmsViewModel.listFilmsTop?.observe(this, Observer {
            it ?: return@Observer
                myAdapter0.updateAdapter(it)
        })
        filmsViewModel.listFilmsBottom?.observe(this, Observer {
            it ?: return@Observer
            myAdapter1.updateAdapter(it)
        })
    }

    companion object {
        fun newInstanse() = FilmsFragment()
    }

    override fun onDestroyView() {

         super.onDestroyView()

    }
}