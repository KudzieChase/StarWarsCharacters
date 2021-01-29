package com.kudzaichasinda.starwarscharacters.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudzaichasinda.starwarscharacters.databinding.ItemFilmBinding
import com.kudzaichasinda.starwarscharacters.model.FilmView
import com.kudzaichasinda.starwarscharacters.model.diff.FilmDiff

class FilmAdapter : ListAdapter<FilmView, FilmAdapter.ItemHolder>(FilmDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
    }

    inner class ItemHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: FilmView) {
            binding.run {
                this.film = film

                executePendingBindings()
            }
        }
    }
}