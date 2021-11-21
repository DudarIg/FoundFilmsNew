package ru.dudar.findfilms.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.data.Ganr
import ru.dudar.findfilms.data.MainBroadcastReceiver
import ru.dudar.findfilms.data.ServiceFilmView
import ru.dudar.findfilms.domain.Disable
import ru.dudar.findfilms.domain.GanrAdapter
import ru.dudar.findfilms.domain.MyAdapter
import java.io.File
import java.security.AccessController

class MainActivity : AppCompatActivity(), MyAdapter.MyHolder.Callbacks, Disable {

    private val receiver = MainBroadcastReceiver()


    lateinit var toolbar: Toolbar
    private var bottomMenuView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        initToolbar()
        initBottomMenu()

        val filmsFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (filmsFragment == null) {

            val fragment = FilmsFragment.newInstanse()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onFilmSelect(film: Film) {
        startService(Intent(this,
                       ServiceFilmView::class.java).putExtra("film", film) )

        val fragment = OneFilmFragment.newInstance(film)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }

    private fun initToolbar() {
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    private fun initBottomMenu() {
        bottomMenuView = findViewById(R.id.bottom_nav_menu)
        bottomMenuView!!.setOnItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.as_programm -> {
                    fragment = ProgrammFragment.newInstance()
                }
                R.id.found_film -> {

                    fragment = FoundFilmFragment.newInstance()
                }
                R.id.as_settings -> {
                    fragment = SettingsFragment.newInstance()
                }
                else -> {
                    fragment = ProgrammFragment.newInstance()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            true
        }
    }

    override fun onDisableButton(bool: Boolean, idMenu: Int) {
        var menuItem = bottomMenuView!!.menu.findItem(idMenu)
        menuItem.isVisible = bool


    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    public fun ffff(): List<String> {
        val path = this.getFilesDir()
        return File(path, "GanrView.txt").readLines()
    }


}
