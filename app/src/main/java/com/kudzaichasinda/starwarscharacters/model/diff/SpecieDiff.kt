package com.kudzaichasinda.starwarscharacters.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.kudzaichasinda.starwarscharacters.model.SpecieView

object SpecieDiff : DiffUtil.ItemCallback<SpecieView>() {
    override fun areItemsTheSame(oldItem: SpecieView, newItem: SpecieView): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: SpecieView, newItem: SpecieView): Boolean =
        oldItem == newItem
}