package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dudar.findfilms.R
import ru.dudar.findfilms.domain.Disable

class FoundFilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_found_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val disable = context as Disable
        disable.onDisableButton(false, R.id.found_film)

    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.found_film)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FoundFilmFragment()
    }
}