package com.kudzaichasinda.starwarscharacters.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudzaichasinda.starwarscharacters.databinding.ItemSpecieBinding
import com.kudzaichasinda.starwarscharacters.model.SpecieView
import com.kudzaichasinda.starwarscharacters.model.diff.SpecieDiff

class SpecieAdapter : ListAdapter<SpecieView, SpecieAdapter.ItemHolder>(SpecieDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemSpecieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val specie = getItem(position)
        holder.bind(specie)
    }

    inner class ItemHolder(private val binding: ItemSpecieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(specie: SpecieView) {
            binding.run {
                this.specie = specie

                executePendingBindings()
            }
        }
    }
}