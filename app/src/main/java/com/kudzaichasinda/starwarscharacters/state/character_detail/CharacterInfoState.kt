package com.kudzaichasinda.starwarscharacters.state.character_detail

import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.mvi.ViewState

data class CharacterInfoState(
    val character: CharacterView? = null,
    val isLoading: Boolean = true
) : ViewState
