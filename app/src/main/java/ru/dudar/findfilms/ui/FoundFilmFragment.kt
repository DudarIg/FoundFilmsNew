package ru.dudar.findfilms.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.TMDBGenresImpl
import ru.dudar.findfilms.databinding.FragmentFoundFilmBinding
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.Themoviedb

class FoundFilmFragment : Fragment(R.layout.fragment_found_film) {

    private val binding by viewBinding(FragmentFoundFilmBinding::bind)
    private val getTheMoviegen: Themoviedb by lazy { TMDBGenresImpl() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.found_film)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)
        ganresLoad()
    }

    private fun ganresLoad() {
        binding.progressBar.isVisible = true
        Thread {
            val resJson = getTheMoviegen.getTMDBGenresSync()
            val sb = StringBuilder()
            resJson.forEach {
                it.genres.forEach() {
                    sb.appendLine("id=${it.id.toString()}  жанр: ${it.name.toString()}")
                }
            }
            runOnUiThread {
                binding.progressBar.isVisible = false
                binding.loadTextView.text = sb.toString()
            }
        }.start()
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return
        activity?.runOnUiThread(action)
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