package ru.dudar.findfilms.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.RetrofitTMDBGenresImpl
import ru.dudar.findfilms.data.TMDBGenresImpl
import ru.dudar.findfilms.databinding.FragmentFoundFilmBinding
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres

class FoundFilmFragment : Fragment(R.layout.fragment_found_film) {

    private val binding by viewBinding(FragmentFoundFilmBinding::bind)
    private val getTheMoviegen: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.found_film)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)


//    ganresLoadSync()
      ganresLoadLiveData()

    }

    private fun ganresLoadLiveData() {
        binding.progressBar.isVisible = true
        getTheMoviegen.getGenres().observe(viewLifecycleOwner) {
            if (it != null) {
                val sb = StringBuilder()
                it.genres.forEach() {
                    sb.appendLine("id=${it.id.toString()}  жанр: ${it.name.toString()}")
                }
                binding.progressBar.isVisible = false
                binding.loadTextView.text = sb.toString()
            } else {
                binding.progressBar.isVisible = false
                Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun ganresLoadSync() {
        binding.progressBar.isVisible = true
        Thread {
            val resJson = getTheMoviegen.getTMDBGenresSync()

            if (resJson != null) {
                val sb = StringBuilder()
                resJson.genres.forEach {
                        sb.appendLine("id=${it.id.toString()}  жанр: ${it.name.toString()}")
                }
                activity?.runOnUiThread {
                    binding.progressBar.isVisible = false
                    binding.loadTextView.text = sb.toString()
                }
            } else {
                activity?.runOnUiThread {
                    binding.progressBar.isVisible = false
                    Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_SHORT).show()
                }
            }

        }.start()
    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.found_film)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.cite)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FoundFilmFragment()
    }
}