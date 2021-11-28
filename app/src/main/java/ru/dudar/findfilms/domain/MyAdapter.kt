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


class MyAdapter(private val films: MutableList<Film>, val dan: Int):
    RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(itemView: View, val holderContext : Context):
        RecyclerView.ViewHolder(itemView) {

        interface Callbacks {
            fun onFilmSelect(film:Film)
        }

        interface CallbacksDelete {
            fun onDeleteFilm(film:Film)
        }



        fun setData(film: Film) {
            val photo: ImageView = itemView.findViewById(R.id.photo_imageview)
            Glide.with(holderContext)
                .load(film.photo)
                .into(photo)
             //title.text = film.style
           // Log.d("@@@@", "${film.ganr}")

            itemView.setOnClickListener {
                val callbacks = holderContext as Callbacks
                callbacks.onFilmSelect(film)
            }
         }

        fun setDataMainFilm(film: Film) {
            val title : TextView = itemView.findViewById(R.id.title_text_view)
            val year : TextView = itemView.findViewById(R.id.ganr_text_view)
//            Glide.with(holderContext)
//                .load(film.photo)
//                .into(photo)
            title.text = film.title
            year.text = film.year
            // Log.d("@@@@", "${film.ganr}")

            itemView.setOnClickListener {
                val callbacksDelete = holderContext as CallbacksDelete
                callbacksDelete.onDeleteFilm(film)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        if (dan == 1)
                return MyHolder(
                    LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item, parent, false), parent.context)
        else
            return MyHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_main_film, parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val film = films[position]
        if (dan == 1) {
            holder.setData(film) }
        if (dan ==2) {
            holder.setDataMainFilm(film)  }
    }

    override fun getItemCount(): Int {
        return films.size
    }
    // обновление адаптера
    fun updateAdapter(listItems: List<Film>){
        films.clear()
        films.addAll(listItems)
        notifyDataSetChanged()
    }


}