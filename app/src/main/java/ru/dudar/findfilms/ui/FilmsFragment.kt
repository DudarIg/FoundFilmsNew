package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar.findfilms.R
import ru.dudar.findfilms.apiTheMovies.ApiBookImpl
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.FilmsFragmentBinding
import ru.dudar.findfilms.domain.GanrOb
import ru.dudar.findfilms.domain.MyAdapter


class FilmsFragment : Fragment(R.layout.films_fragment) {

    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!
    var myAdapter0 = MyAdapter(mutableListOf<Film>())
    var myAdapter1 = MyAdapter(mutableListOf<Film>())

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
        ApiBookImpl().filmsGange(GanrOb.ganrOb[0]).observe(this,
            Observer {
                it ?: return@Observer
                myAdapter0.updateAdapter(it)
            })
        ApiBookImpl().filmsGange(GanrOb.ganrOb[1]).observe(this, Observer {
            it ?: return@Observer
            myAdapter1.updateAdapter(it)
        })
    }

    companion object {
        fun newInstanse() = FilmsFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}