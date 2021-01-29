package com.kudzaichasinda.starwarscharacters.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class SearchViewModel @ViewModelInject constructor(
    private val searchCharacter: SearchCharacter,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Result<List<CharacterView>>>(Result.Idle)
    val searchResults: StateFlow<Result<List<CharacterView>>>
        get() = _searchResults

    private val searchInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchInput.debounce(500).collect {
                if (it.isEmpty()) {
                    _searchResults.value = Result.Idle
                }else{
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