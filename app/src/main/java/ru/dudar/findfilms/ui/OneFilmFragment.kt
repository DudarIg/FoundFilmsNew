package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.ActivityFilmBinding


private const val ARG_PARAM = "param"

class OneFilmFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var film: Film? = null
    private lateinit var binding: ActivityFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            film = it.getSerializable(ARG_PARAM) as Film
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityFilmBinding.bind(view)

        binding.filmImageView.setImageResource(film!!.photo)
        binding.titleTextView.text = film!!.title
        binding.yearTextView.text = film!!.year.toString()
        binding.countryTextView.text = film!!.country
        binding.styleTextView.text = film!!.style

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
}