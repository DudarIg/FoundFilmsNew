package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.databinding.ActivityFilmBinding
import ru.dudar.findfilms.apiTheMovies.GanresViewModel

private const val ARG_PARAM = "param"

class OneFilmFragment : Fragment(R.layout.activity_film) {

    private val ganrViewModel by viewModels<GanresViewModel>()

    private var _binding: ActivityFilmBinding? = null
    private val binding get() = _binding!!
    private var ganrFilm: String = ""
    private var ganry: ArrayList<Ganr> = arrayListOf()
    private var film: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            film = it.getSerializable(ARG_PARAM) as Film
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ActivityFilmBinding.bind(view)

        infoFilms()
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

    private fun infoFilms() {
        Glide.with(this)
            .load(film!!.photo)
            .into(binding.filmImageView)
        binding.titleTextView.text = film!!.title
        binding.yearTextView.text = film!!.year

        ganrViewModel.listGanresVM.observe(viewLifecycleOwner, Observer {
            it.forEach { ganr ->
                if (film!!.ganr == ganr.id) {
                    binding.styleTextView.text = ganr.name
                }
            }
        })
    }

}