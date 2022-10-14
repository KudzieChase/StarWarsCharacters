package com.kudzaichasinda.starwarscharacters.state.character_detail

import com.kudzaichasinda.starwarscharacters.model.FilmView

data class FilmInfoState(
    val films: List<FilmView> = listOf(),
    val isLoading: Boolean = true
)
