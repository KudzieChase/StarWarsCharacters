package com.kudzaichasinda.starwarscharacters.state

import com.kudzaichasinda.starwarscharacters.model.CharacterView

data class SearchScreenUiState(
    val isIdle: Boolean = true,
    val isLoading: Boolean = false,
    val searchText: String = "",
    val searchResults: List<CharacterView> = emptyList(),
    val error: String? = null
) {
    val loading: SearchScreenUiState
        get() = copy(isIdle = false, isLoading = true)

    val idle: SearchScreenUiState
        get() = copy(isIdle = true, isLoading = false)

    fun success(results: List<CharacterView>): SearchScreenUiState =
        copy(isIdle = true, isLoading = false, searchResults = results)

    fun hasError(message: String): SearchScreenUiState =
        copy(isIdle = true, isLoading = false, error = error)
}
