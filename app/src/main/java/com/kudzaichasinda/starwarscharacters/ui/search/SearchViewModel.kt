package com.kudzaichasinda.starwarscharacters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.intent.SearchIntent
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.middleware.LoggingMiddleware
import com.kudzaichasinda.starwarscharacters.middleware.SearchMiddleware
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.mvi.Store
import com.kudzaichasinda.starwarscharacters.reducer.SearchReducer
import com.kudzaichasinda.starwarscharacters.state.SearchUiViewState
import com.kudzaichasinda.starwarscharacters.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchCharacter: SearchCharacter,
    mapper: CharacterViewMapper
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Result<List<CharacterView>>>(Result.Idle)
    val searchResults: StateFlow<Result<List<CharacterView>>>
        get() = _searchResults

    private val searchInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchInput.debounce(500).collect {
                dispatch(action = SearchIntent.SearchInputChanged(newText = it))
            }
        }
    }

    fun performSearch(characterName: String) {
        viewModelScope.launch {
            searchInput.emit(characterName)
        }
    }

    // ---------------------------------------------------------
    // For the MVI stuff.

    private val store = Store(
        initialState = SearchUiViewState(),
        reducer = SearchReducer(),
        middlewares = listOf(
            SearchMiddleware(searchCharacter = searchCharacter, mapper = mapper),
            LoggingMiddleware()
        )
    )

    val viewState: StateFlow<SearchUiViewState> = store.state

    private suspend fun dispatch(action: SearchIntent) {
        store.dispatch(intent = action)
    }
}
