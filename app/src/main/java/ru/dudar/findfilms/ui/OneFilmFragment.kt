package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.ActivityFilmBinding
import java.io.File
import java.io.FileOutputStream

private const val ARG_PARAM = "param"

class OneFilmFragment : Fragment(R.layout.activity_film) {
    private var film: Film? = null
    // Without reflection
    private val binding by viewBinding(ActivityFilmBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            film = it.getSerializable(ARG_PARAM) as Film
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(film!!.photo)
            .into(binding.filmImageView)
        //binding.filmImageView.setImageResource(film!!.photo)
        binding.titleTextView.text = film!!.title
        binding.yearTextView.text = film!!.year
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