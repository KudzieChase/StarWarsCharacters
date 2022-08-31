package com.kudzaichasinda.starwarscharacters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacter: SearchCharacter,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Result<List<CharacterView>>>(Result.Idle)
    val searchResults: StateFlow<Result<List<CharacterView>>>
        get() = _searchResults

    private val searchInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            // Debounce the input to reduce unnecessary requests to the server
            searchInput.debounce(500).collect {
                if (it.isEmpty()) {
                    _searchResults.value = Result.Idle
                } else {
                    searchCharacter(it)
                        .catch { throwable ->
                            if (throwable is UnknownHostException) {
                                _searchResults.value =
                                    Result.Error("Failed: No Internet Connection.")
                            } else {
                                _searchResults.value = Result.Error(throwable.message)
                            }
                        }
                        .collect { character ->
                            _searchResults.value = Result.Success(mapper.mapToViewList(character))
                        }
                }
            }
        }
    }

    /**
     * If the name is empty then immediately return the Idle State
     * Otherwise emit the new name
     * */
    fun performSearch(characterName: String) {
        _searchResults.value = Result.Loading

        viewModelScope.launch {
            if (characterName.isEmpty()) {
                _searchResults.value = Result.Idle
            } else {
                searchInput.emit(characterName)
            }
        }
    }
}
