package com.example.moviecolabs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecolabs.databinding.ItemFilmBinding
import com.example.moviecolabs.model.getResponFilmItem

class adapterFilm(var listFilm : List<getResponFilmItem>):RecyclerView.Adapter<adapterFilm.ViewHolder>() {
    class ViewHolder(var binding: ItemFilmBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNama.text = listFilm[position].name
        holder.binding.tvdate.text = listFilm[position].date
        holder.binding.tvdirec.text = listFilm[position].director
        holder.binding.tvdesc.text = listFilm[position].description
//        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.tvImg)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}