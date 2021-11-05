package ru.dudar.findfilms.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.FilmsViewModel
import ru.dudar.findfilms.domain.MyAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filmsViewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val recyclerViewTop: RecyclerView = findViewById(R.id.recycler_top)
            recyclerViewTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewTop.adapter = MyAdapter(filmsViewModel.films_top, this)

            val recyclerViewBottom: RecyclerView = findViewById(R.id.recycler_bottom)
            recyclerViewBottom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewBottom.adapter = MyAdapter(filmsViewModel.films_bottom, this)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val recyclerViewTop: RecyclerView = findViewById(R.id.recycler_top)
            recyclerViewTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewTop.adapter = MyAdapter(filmsViewModel.films_top + filmsViewModel.films_bottom, this)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }
}