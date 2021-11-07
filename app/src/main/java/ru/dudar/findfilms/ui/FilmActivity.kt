package ru.dudar.findfilms.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.databinding.ActivityFilmBinding
import java.util.*

private const val EXTRA_FILM = "extra_film"

class FilmActivity : AppCompatActivity() {

   private lateinit var binding:ActivityFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        val film = intent.getSerializableExtra(EXTRA_FILM) as Film

        binding.filmImageView.setImageResource(film.photo)
        binding.titleTextView.text = film.title
        binding.yearTextView.text = film.year.toString()
        binding.countryTextView.text = film.country
        binding.styleTextView.text = film.style

    }
    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }

    companion object {
        fun newIntent(packContext: Context, film: Film): Intent {
            return Intent(packContext, FilmActivity::class.java).apply {
                putExtra(EXTRA_FILM, film)
            }
        }
    }
}