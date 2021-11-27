package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dudar.findfilms.R
import ru.dudar.findfilms.apiBook.GanresViewModel
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.databinding.FragmentFoundFilmBinding
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.GanrAdapter
import ru.dudar.findfilms.domain.GanrOb


class FoundFilmFragment : Fragment(R.layout.fragment_found_film) {

    private var _binding: FragmentFoundFilmBinding? = null
    private val binding get() = _binding!!
    private val sb: MutableList<Ganr> = mutableListOf()
    private val ganrViewModel by viewModels<GanresViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFoundFilmBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.found_film)

        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)

        ganresLoadSync()

    }

    private fun ganresLoadSync() {

        binding.progressBar.isVisible = true

        ganrViewModel.listGanresVM.observe(viewLifecycleOwner, Observer {
            it.forEach {
                if (it.id == GanrOb.ganrOb[0] || it.id == GanrOb.ganrOb[1])
                    sb.add(Ganr(it.id, it.name, true))
                else
                    sb.add(Ganr(it.id, it.name, false))
            }
            binding.progressBar.isVisible = false
            binding.recyclerGanr.layoutManager = LinearLayoutManager(context)
            binding.recyclerGanr.adapter = GanrAdapter(sb)
        }
        )
    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.found_film)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.cite)
        var i = 0
        sb.forEach {
            if (it.viv && i < 2) {
                GanrOb.ganrOb[i] = it.id
                i++
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FoundFilmFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}