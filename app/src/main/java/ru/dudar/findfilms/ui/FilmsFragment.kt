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
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.FilmsViewModel
import ru.dudar.findfilms.domain.MyAdapter

class FilmsFragment : Fragment() {
    private lateinit var recyclerViewTop: RecyclerView
    private lateinit var recyclerViewBottom: RecyclerView
    private lateinit var filmsViewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.films_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsViewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)
        recyclers_init(view)

    }

    private fun recyclers_init(view: View) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerViewTop = view.findViewById(R.id.recycler_top)
            recyclerViewTop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewTop.adapter = MyAdapter(filmsViewModel.films_top)

            recyclerViewBottom = view.findViewById(R.id.recycler_bottom)
            recyclerViewBottom.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewBottom.adapter = MyAdapter(filmsViewModel.films_bottom)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerViewTop = view.findViewById(R.id.recycler_top)
            recyclerViewTop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewTop.adapter = MyAdapter(filmsViewModel.films_top + filmsViewModel.films_bottom)

        }

    }

    companion object {
        fun newInstanse() = FilmsFragment()
    }
}