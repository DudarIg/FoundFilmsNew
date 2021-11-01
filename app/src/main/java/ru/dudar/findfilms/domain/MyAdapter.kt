package ru.dudar.findfilms.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Film



class MyAdapter(private val films: List<Film>, private val myContext: Context):
    RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo_imageview)
        val title : TextView = itemView.findViewById(R.id.title_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val film = films[position]
        holder.photo.setImageResource(film.photo)
        holder.title.text = film.title
//        holder.itemView.setOnClickListener {
//            val intent = OneActivity.newIntent(myContext, cat.name, cat.color, age, cat.date)
//            myContext.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return films.size
    }
}