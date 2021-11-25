package ru.dudar.findfilms.ui

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.FilmsViewModel
import ru.dudar.findfilms.databinding.FilmsFragmentBinding
import ru.dudar.findfilms.domain.GanrOb.ganrOb
import ru.dudar.findfilms.domain.MyAdapter

class FilmsFragment : Fragment(R.layout.films_fragment) {
    private lateinit var filmsViewModel: FilmsViewModel

    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FilmsFragmentBinding.bind(view)

        filmsViewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)
        filmsViewModel.getData()
        SystemClock.sleep(5_000)

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}