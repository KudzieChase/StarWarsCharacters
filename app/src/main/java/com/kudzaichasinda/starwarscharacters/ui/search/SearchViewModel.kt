package com.kudzaichasinda.starwarscharacters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.state.SearchScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacter: SearchCharacter,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenUiState())
    val state: StateFlow<SearchScreenUiState>
        get() = _state

    private val searchInput = MutableStateFlow("")

    init {
        // Debounce the input to reduce unnecessary requests to the server
        searchInput.debounce(500).onEach {
            searchCharacter(it).onEach { result ->
                _state.value = when (result) {
                    is Resource.Error ->
                        SearchScreenUiState().hasError(message = result.message)

                    Resource.Loading ->
                        SearchScreenUiState().loading

                    is Resource.Success ->
                        SearchScreenUiState().success(
                            results = mapper.mapToViewList(
                                list = result.data
                            )
                        )
                }
            }.launchIn(viewModelScope)
        }
    }

    /**
     * If the name is empty then immediately return the Idle State
     * Otherwise emit the new name
     * */
    fun performSearch(characterName: String) {
        viewModelScope.launch {
            if (characterName.isEmpty()) {
                _state.value = SearchScreenUiState().idle
            } else {
                searchInput.emit(characterName)
            }
        }
    }
}
