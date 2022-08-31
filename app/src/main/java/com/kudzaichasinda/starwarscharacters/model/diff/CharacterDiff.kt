package com.kudzaichasinda.starwarscharacters.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.kudzaichasinda.starwarscharacters.model.CharacterView

object CharacterDiff : DiffUtil.ItemCallback<CharacterView>() {
    override fun areItemsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean =
        oldItem == newItem
}
