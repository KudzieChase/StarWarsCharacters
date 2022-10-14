package com.kudzaichasinda.starwarscharacters.state

import com.kudzaichasinda.starwarscharacters.mvi.ViewState
import com.kudzaichasinda.starwarscharacters.state.character_detail.CharacterInfoState
import com.kudzaichasinda.starwarscharacters.state.character_detail.FilmInfoState
import com.kudzaichasinda.starwarscharacters.state.character_detail.PlanetInfoState

/**
 * An implementation of [ViewState] that describves the configuration of our Character Detail Screen
 * at a given time.
 */
data class CharacterDetailUiViewState(
    val characterInfoState: CharacterInfoState,
    val filmInfoState: FilmInfoState,
    val planetInfoState: PlanetInfoState
) : ViewState
