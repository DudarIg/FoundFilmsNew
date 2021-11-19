package ru.dudar.findfilms.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dudar.findfilms.R
import ru.dudar.findfilms.data.Ganr

class GanrAdapter(private val ganr: List<Ganr>):
    RecyclerView.Adapter<GanrAdapter.MyHolder>() {

    class MyHolder(itemView: View, val holderContext : Context):
        RecyclerView.ViewHolder(itemView) {

//        interface Callbacks {
//            fun onGanrSelect(film: Film)
//        }

        val id: TextView = itemView.findViewById(R.id.id_text_view)
        val name : TextView = itemView.findViewById(R.id.name_text_view)
        val viv : CheckBox = itemView.findViewById(R.id.viv_checkBox)


        fun setData(ganr: Ganr) {
            id.text = ganr.id.toString()
            name.text = ganr.name
            viv.isChecked = ganr.viv


//            itemView.setOnClickListener {
//                val callbacks = holderContext as Callbacks
//                callbacks.onGanrSelect(ganr)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_ganr, parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val film = ganr[position]
        holder.setData(film)
    }

    override fun getItemCount(): Int {
        return ganr.size
    }


}