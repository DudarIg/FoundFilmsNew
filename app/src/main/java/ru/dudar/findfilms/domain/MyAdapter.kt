package ru.dudar.findfilms.domain

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film
import ru.dudar.findfilms.ui.FilmActivity
import ru.dudar.findfilms.ui.MainActivity
import ru.dudar.findfilms.ui.OneFilmFragment



class MyAdapter(private val films: List<Film>):
    RecyclerView.Adapter<MyAdapter.MyHolder>() {






    class MyHolder(itemView: View, val holderContext : Context):
        RecyclerView.ViewHolder(itemView) {

        interface Callbacks {
            fun onFilmSelect(film:Film)
        }
        private var callbacks: Callbacks? = null

        val photo: ImageView = itemView.findViewById(R.id.photo_imageview)
        val title : TextView = itemView.findViewById(R.id.title_textview)

        fun setData(film: Film) {
             photo.setImageResource(film.photo)
             title.text = film.title

            itemView.setOnClickListener {
//                val fragment = OneFilmFragment.newInstance(film)
//                (holderContext as MainActivity).supportFragmentManager
//                    .beginTransaction().replace(R.id.fragment_container, fragment)
//                    .addToBackStack(null)
//                    .commit()
                callbacks = holderContext as Callbacks
                callbacks?.onFilmSelect(film)
            }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val film = films[position]
        holder.setData(film)
    }

    override fun getItemCount(): Int {
        return films.size
    }


}