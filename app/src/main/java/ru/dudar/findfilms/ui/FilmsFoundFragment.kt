package ru.dudar.findfilms.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dudar.findfilms.R

import ru.dudar.findfilms.api.KinopoiskRepo

private const val TAG = "WWW"

class FilmsFoundFragment : Fragment() {

    private lateinit var recyclerViewTop: RecyclerView
    private lateinit var recyclerViewBottom: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kinopoiskLiveData: LiveData<String> = KinopoiskRepo().popularCallback()

        kinopoiskLiveData.observe(this,
        Observer { responseString ->
            Log.d(TAG, "Ответ сервера: $responseString")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? { return inflater.inflate(R.layout.films_fragment, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewTop = view.findViewById(R.id.recycler_top)
        recyclerViewTop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    }

    companion object {
        @JvmStatic
        fun newInstance() = FilmsFoundFragment()
    }
}