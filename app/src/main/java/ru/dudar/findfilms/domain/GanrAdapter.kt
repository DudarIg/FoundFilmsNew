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

class GanrAdapter(private val ganrs: List<Ganr>):
    RecyclerView.Adapter<GanrAdapter.MyHolder>() {

    class MyHolder(itemView: View, val hContext : Context):
        RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.title_text_view)
        val name : TextView = itemView.findViewById(R.id.name_text_view)
        val viv : CheckBox = itemView.findViewById(R.id.viv_checkBox)

        fun setData(ganr: Ganr) {
            id.text = ganr.id.toString()
            name.text = ganr.name
            viv.isChecked = ganr.viv

            itemView.setOnClickListener {
               if (viv.isChecked) {
                     viv.isChecked = false
                     ganr.viv = false
                 }
                    else {
                    viv.isChecked = true
                    ganr.viv = true}

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_ganr, parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val ganr = ganrs[position]
        holder.setData(ganr)
    }

    override fun getItemCount(): Int {
        return ganrs.size
    }


}