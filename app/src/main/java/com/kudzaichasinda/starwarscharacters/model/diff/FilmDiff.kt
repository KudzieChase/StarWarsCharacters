package com.kudzaichasinda.starwarscharacters.model.diff

import androidx.recyclerview.widget.DiffUtil
import com.kudzaichasinda.starwarscharacters.model.FilmView

object FilmDiff : DiffUtil.ItemCallback<FilmView>() {
    override fun areItemsTheSame(oldItem: FilmView, newItem: FilmView): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: FilmView, newItem: FilmView): Boolean =
        oldItem == newItem
}
