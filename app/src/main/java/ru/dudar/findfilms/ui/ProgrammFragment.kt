package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dudar.findfilms.R
import ru.dudar.findfilms.domain.Disable

class ProgrammFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_programm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val disable = context as Disable
        disable.onDisableButton(false, R.id.as_programm)

    }

    companion object {
        @JvmStatic
        fun newInstance() =  ProgrammFragment()
    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.as_programm)
    }
}

