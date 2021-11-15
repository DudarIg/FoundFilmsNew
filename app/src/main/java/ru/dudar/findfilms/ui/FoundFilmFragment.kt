package ru.dudar.findfilms.ui

import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Example
import ru.dudar.findfilms.databinding.FragmentFoundFilmBinding

import ru.dudar.findfilms.domain.Disable
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FoundFilmFragment : Fragment(R.layout.fragment_found_film) {

    private val gson by lazy { Gson() }
    private val binding by viewBinding(FragmentFoundFilmBinding::bind)
    private val theMovieDb = "https://api.themoviedb.org/3/genre/" +
                             "movie/list?api_key=13826f824fac01ddb5cfe3a61935b835&language=ru"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.found_film)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)
        ganresLoad()
    }

    private fun ganresLoad() {
        binding.progressBar.isVisible = true
        Thread {
            var connection: HttpURLConnection? = null
            try {
                val url = URL(theMovieDb)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5_000
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                val result = bufferedReader.readLines().toString()
                val resJson = gson.fromJson(result, Array<Example>::class.java)
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
            } catch (ex: Exception) {
                Snackbar.make(binding.root, "Ошибка получения данных", Snackbar.LENGTH_LONG).show()
            }
            finally {
                connection?.disconnect()
            }
        }.start()
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
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