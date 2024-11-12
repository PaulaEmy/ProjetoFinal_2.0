package com.example.projetofinal

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView


class NightmareAdapter(private val nightmares: List<MainActivity.NightmaresTable>, private val onClickListener: (MainActivity.NightmaresTable) -> Unit) :
    RecyclerView.Adapter<NightmareAdapter.NightmareViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NightmareViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nightmare, parent, false)
        return NightmareViewHolder(view)
    }

    override fun onBindViewHolder(holder: NightmareViewHolder, position: Int) {
        val nightmare = nightmares[position]
        holder.bind(nightmare, onClickListener)
    }

    override fun getItemCount(): Int = nightmares.size

    class NightmareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.textViewTitle)
        private val date: TextView = itemView.findViewById(R.id.textViewDate)
        private val terrorRating: TextView = itemView.findViewById(R.id.textViewTerrorRating)

        fun bind(nightmare: MainActivity.NightmaresTable, onClickListener: (MainActivity.NightmaresTable) -> Unit) {
            title.text = nightmare.title
            date.text = nightmare.date
            terrorRating.text = "Classificação de Terror: ${nightmare.terrorRating}"
            itemView.setOnClickListener { onClickListener(nightmare) }
        }
    }
}

