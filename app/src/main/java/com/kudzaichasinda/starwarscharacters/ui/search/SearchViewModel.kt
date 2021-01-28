package com.kudzaichasinda.starwarscharacters.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchCharacter: SearchCharacter,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Result<List<CharacterView>>>(Result.Idle)
    val searchResults: StateFlow<Result<List<CharacterView>>>
        get() = _searchResults

    fun performSearch(characterName: String) {
        _searchResults.value = Result.Loading
        viewModelScope.launch {
            searchCharacter(characterName)
                .debounce(500)
                .mapLatest {
                    _searchResults.value = Result.Success(mapper.mapToViewList(it))
                }.catch { throwable ->
                    _searchResults.value = Result.Error(throwable.message)
                }
        }
    }

}