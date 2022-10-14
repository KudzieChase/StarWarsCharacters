package com.kudzaichasinda.starwarscharacters.state

import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.mvi.ViewState

/**
 * An implementation of [ViewState] that describes the configuration of our Search Screen
 */
data class SearchUiViewState(
    val searchInputText: String = "",
    val isLoading: Boolean = false,
    val isIdle: Boolean = true,
    val searchError: String? = null,
    val searchResults: List<CharacterView> = emptyList()
) : ViewState
