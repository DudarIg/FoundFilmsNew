package ru.dudar.findfilms.domain

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film


class MyAdapter(private val films: List<Film>):
    RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(itemView: View, val holderContext : Context):
        RecyclerView.ViewHolder(itemView) {

        interface Callbacks {
            fun onFilmSelect(film:Film)
        }

        val photo: ImageView = itemView.findViewById(R.id.photo_imageview)
        //val title : TextView = itemView.findViewById(R.id.title_textview)

        fun setData(film: Film) {
            Glide.with(holderContext)
                .load(film.photo)
                .into(photo)
             //title.text = film.style
            Log.d("@@@@", "${film.ganr}")

            itemView.setOnClickListener {
                val callbacks = holderContext as Callbacks
                callbacks.onFilmSelect(film)
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