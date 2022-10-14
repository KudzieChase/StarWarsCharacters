package com.kudzaichasinda.starwarscharacters.state.character_detail

import com.kudzaichasinda.starwarscharacters.model.PlanetView

data class PlanetInfoState(
    val planet: PlanetView? = null,
    val isLoading: Boolean = true
)
