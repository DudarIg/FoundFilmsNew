package ru.dudar.findfilms.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.data.RetrofitTMDBGenresImpl
import ru.dudar.findfilms.data.TMDBGenresImpl
import ru.dudar.findfilms.databinding.FragmentFoundFilmBinding
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.GanrAdapter
import ru.dudar.findfilms.domain.MyAdapter
import ru.dudar.findfilms.domain.TMDBGenres
import ru.dudar.findfilms.domain.Themoviesgenres.TheMovieGenres
import java.io.File

class FoundFilmFragment : Fragment(R.layout.fragment_found_film) {

    private val binding by viewBinding(FragmentFoundFilmBinding::bind)
    private val getTheMoviegen: TMDBGenres by lazy { RetrofitTMDBGenresImpl() }
    private val sb: MutableList<Ganr> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.found_film)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)

        //       ganresLoadSync()
        ganresLoadLiveData()

    }

    private fun ganresLoadLiveData() {
        var ganrView: List<String>
        binding.progressBar.isVisible = true
        getTheMoviegen.getGenres().observe(viewLifecycleOwner) {
            if (it != null) {
                val path = context?.getFilesDir()
                val file = File(path, "GanrView.txt")
                ganrView = file.readLines()

                it.genres.forEach() {
                    if (it.id == ganrView[0].toInt() || it.id == ganrView[1].toInt())
                        sb.add(Ganr(it.id, it.name, true))
                    else
                        sb.add(Ganr(it.id, it.name, false))
                }
                binding.progressBar.isVisible = false
                binding.recyclerGanr.layoutManager = LinearLayoutManager(context)
                binding.recyclerGanr.adapter = GanrAdapter(sb)
            } else {
                binding.progressBar.isVisible = false
                Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun ganresLoadSync() {

        binding.progressBar.isVisible = true
        var ganrView: List<String>
        Thread {
            val resJson = getTheMoviegen.getTMDBGenresSync()

            if (resJson != null) {
                val path = context?.getFilesDir()
                val file = File(path, "GanrView.txt")
                ganrView = file.readLines()

                resJson.genres.forEach {
                    if (it.id == ganrView[0].toInt() || it.id == ganrView[1].toInt())
                        sb.add(Ganr(it.id, it.name, true))
                    else
                        sb.add(Ganr(it.id, it.name, false))
                }

                activity?.runOnUiThread {
                    binding.progressBar.isVisible = false

                }

            } else {
                activity?.runOnUiThread {
                    binding.progressBar.isVisible = false
                    Snackbar.make(binding.root, "Ошибка сети", Snackbar.LENGTH_SHORT).show()
                }
            }
        }.start()

        binding.recyclerGanr.layoutManager = LinearLayoutManager(context)
        binding.recyclerGanr.adapter = GanrAdapter(sb)
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