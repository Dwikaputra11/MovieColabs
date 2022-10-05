package com.example.moviecolabs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecolabs.R
import com.example.moviecolabs.Util.MenuQuery
import com.example.moviecolabs.data.Film
import com.example.moviecolabs.databinding.ItemFilmBinding
import com.example.moviecolabs.model.ResponseFilmItem

class AdapterFilm:RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(menu: MenuQuery, film: ResponseFilmItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private var diffCallback = object : DiffUtil.ItemCallback<ResponseFilmItem>(){
        override fun areItemsTheSame(
            oldItem: ResponseFilmItem,
            newItem: ResponseFilmItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseFilmItem,
            newItem: ResponseFilmItem
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder(var binding: ItemFilmBinding):RecyclerView.ViewHolder(binding.root) {
        init {
            binding.edit.setOnClickListener {
                listener.onItemClick(MenuQuery.UPDATE,differ.currentList[adapterPosition])
            }
            binding.delete.setOnClickListener {
                listener.onItemClick(MenuQuery.DELETE,differ.currentList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNama.text = differ.currentList[position].name
        holder.binding.tvdate.text = differ.currentList[position].date
        holder.binding.tvdirec.text = differ.currentList[position].director
        holder.binding.tvdesc.text = differ.currentList[position].description
        Glide.with(holder.binding.root.context)
            .load(differ.currentList[position].image)
            .into(holder.binding.tvImg)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setFilmList(list: List<ResponseFilmItem>) = differ.submitList(list)
}