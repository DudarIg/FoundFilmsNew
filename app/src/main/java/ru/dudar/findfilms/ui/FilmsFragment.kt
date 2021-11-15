package ru.dudar.findfilms.ui

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.FilmsViewModel
import ru.dudar.findfilms.databinding.ActivityFilmBinding
import ru.dudar.findfilms.databinding.FilmsFragmentBinding
import ru.dudar.findfilms.domain.MyAdapter

class FilmsFragment : Fragment(R.layout.films_fragment) {
    private lateinit var filmsViewModel: FilmsViewModel

    private val binding by viewBinding(FilmsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsViewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)
        recyclers_init(view)

    }

    private fun recyclers_init(view: View) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerTop.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerTop.adapter = MyAdapter(filmsViewModel.films_top)

            binding.recyclerBottom?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerBottom?.adapter = MyAdapter(filmsViewModel.films_bottom)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerTop.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerTop.adapter =
                MyAdapter(filmsViewModel.films_top + filmsViewModel.films_bottom)
        }
    }

    companion object {
        fun newInstanse() = FilmsFragment()
    }
}