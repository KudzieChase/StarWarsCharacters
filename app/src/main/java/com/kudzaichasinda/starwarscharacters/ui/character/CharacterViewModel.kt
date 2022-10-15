package com.kudzaichasinda.starwarscharacters.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetCharacterInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetFilmInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetPlanetInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetSpecieInfo
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.FilmViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.PlanetViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.SpecieViewMapper
import com.kudzaichasinda.starwarscharacters.state.CharacterScreenUiState
import com.kudzaichasinda.starwarscharacters.state.CharacterViewUiState
import com.kudzaichasinda.starwarscharacters.state.FilmsViewUiState
import com.kudzaichasinda.starwarscharacters.state.PlanetViewUiState
import com.kudzaichasinda.starwarscharacters.state.SpeciesViewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterInfo: GetCharacterInfo,
    private val getFilmInfo: GetFilmInfo,
    private val getPlanetInfo: GetPlanetInfo,
    private val getSpecieInfo: GetSpecieInfo,
    private val characterMapper: CharacterViewMapper,
    private val specieMapper: SpecieViewMapper,
    private val filmMapper: FilmViewMapper,
    private val planetMapper: PlanetViewMapper
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterScreenUiState())
    val state: StateFlow<CharacterScreenUiState>
        get() = _state

    fun getCharacter(url: String) {
        getCharacterInfo(url).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = CharacterScreenUiState()
                    .characterState(
                        state = CharacterViewUiState().hasError(
                            error = result.message
                        )
                    )

                Resource.Loading -> _state.value = CharacterScreenUiState().characterState(
                    state = CharacterViewUiState().loading
                )

                is Resource.Success -> _state.value = CharacterScreenUiState()
                    .characterState(
                        state = CharacterViewUiState()
                            .success(
                                characterMapper.mapToView(domain = result.data)
                            )
                    )
            }
        }.launchIn(viewModelScope)
    }

    fun getFilms(urls: List<String>) {
        getFilmInfo(urls).onEach { result ->
            _state.value = when (result) {
                is Resource.Success ->
                    CharacterScreenUiState()
                        .filmsState(
                            state = FilmsViewUiState().success(
                                films =
                                filmMapper.mapToViewList(list = result.data)
                            )
                        )

                is Resource.Loading -> CharacterScreenUiState()
                    .filmsState(
                        state = FilmsViewUiState().loading
                    )

                is Resource.Error -> CharacterScreenUiState()
                    .filmsState(
                        state = FilmsViewUiState().hasError(error = result.message)
                    )
            }
        }.launchIn(viewModelScope)
    }

    fun getHomeWorld(url: String) {
        getPlanetInfo(url).onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> CharacterScreenUiState()
                    .planetState(
                        state = PlanetViewUiState().success(
                            planet = planetMapper.mapToView(domain = result.data)
                        )
                    )

                is Resource.Loading -> CharacterScreenUiState()
                    .planetState(
                        state = PlanetViewUiState().loading
                    )

                is Resource.Error -> CharacterScreenUiState()
                    .planetState(
                        state = PlanetViewUiState().hasError(
                            error = result.message
                        )
                    )
            }
        }.launchIn(viewModelScope)
    }

    fun getSpecies(urls: List<String>) {
        getSpecieInfo(urls).onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> CharacterScreenUiState()
                    .speciesState(
                        state = SpeciesViewUiState().success(
                            species = specieMapper.mapToViewList(list = result.data)
                        )
                    )

                is Resource.Loading -> CharacterScreenUiState()
                    .speciesState(
                        state = SpeciesViewUiState().loading
                    )

                is Resource.Error -> CharacterScreenUiState()
                    .speciesState(
                        state = SpeciesViewUiState().hasError(
                            error = result.message
                        )
                    )
            }
        }.launchIn(viewModelScope)
    }
}
