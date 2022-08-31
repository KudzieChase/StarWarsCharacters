package com.kudzaichasinda.starwarscharacters.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudzaichasinda.starwarscharacters.databinding.ItemSearchResultBinding
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.model.diff.CharacterDiff

class SearchResultAdapter(
    val onItemClick: (CharacterView) -> Unit
) : ListAdapter<CharacterView, SearchResultAdapter.ItemHolder>(CharacterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class ItemHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterView) {
            binding.run {
                this.character = character

                itemClick(character)
                executePendingBindings()
            }
        }

        private fun itemClick(character: CharacterView) {
            itemView.setOnClickListener {
                onItemClick(character)
            }
        }
    }
}
