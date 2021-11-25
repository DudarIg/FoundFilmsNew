package ru.dudar.findfilms.ui

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.data.RetrofitTMDBGenresImpl
import ru.dudar.findfilms.databinding.ActivityFilmBinding
import ru.dudar.findfilms.domain.GanrAdapter
import ru.dudar.findfilms.domain.GanrOb
import ru.dudar.findfilms.domain.TMDBGenres

private const val ARG_PARAM = "param"

class OneFilmFragment : Fragment(R.layout.activity_film) {
    private val getTheMoviegen: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }

    private var _binding: ActivityFilmBinding? = null
    private val binding get() = _binding!!
    private var ganrFilm : String =""
    private var film: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            film = it.getSerializable(ARG_PARAM) as Film
        }
        getTheMoviegen.getGenres().observeForever {
            if (it != null) {
                it.genres.forEach {
                    if (it.id == film!!.ganr.toInt())
                        ganrFilm = it.name
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ActivityFilmBinding.bind(view)


//        getTheMoviegen.getGenres().observe(viewLifecycleOwner) {
//            if (it != null) {
//                it.genres.forEach {
//                    if (it.id == film!!.style.toInt())
//                        ganrFilm = it.name
//                }
//            }
//        }

        Glide.with(this)
            .load(film!!.photo)
            .into(binding.filmImageView)
        binding.titleTextView.text = film!!.title
        binding.yearTextView.text = film!!.year
        binding.styleTextView.text = ganrFilm
    }

    companion object {
        @JvmStatic
        fun newInstance(film: Film) =
            OneFilmFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM, film)
                }
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}