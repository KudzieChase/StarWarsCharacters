package com.kudzaichasinda.starwarscharacters.ui.character

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetCharacterInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetFilmInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetPlanetInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetSpecieInfo
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.FilmViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.PlanetViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.SpecieViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.model.FilmView
import com.kudzaichasinda.starwarscharacters.model.PlanetView
import com.kudzaichasinda.starwarscharacters.model.SpecieView
import com.kudzaichasinda.starwarscharacters.util.Result
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val getCharacterInfo: GetCharacterInfo,
    private val getFilmInfo: GetFilmInfo,
    private val getPlanetInfo: GetPlanetInfo,
    private val getSpecieInfo: GetSpecieInfo,
    private val characterMapper: CharacterViewMapper,
    private val specieMapper: SpecieViewMapper,
    private val filmMapper: FilmViewMapper,
    private val planetMapper: PlanetViewMapper
) : ViewModel() {

    private val _characterLiveData = MutableLiveData<Result<CharacterView>>()
    val characterLiveData: LiveData<Result<CharacterView>>
        get() = _characterLiveData

    private val _filmLiveData = MutableLiveData<Result<List<FilmView>>>()
    val filmLiveData: LiveData<Result<List<FilmView>>>
        get() = _filmLiveData

    private val _planetLiveData = MutableLiveData<Result<PlanetView>>()
    val planetLiveData: LiveData<Result<PlanetView>>
        get() = _planetLiveData

    private val _specieLiveData = MutableLiveData<Result<List<SpecieView>>>()
    val specieLiveData: LiveData<Result<List<SpecieView>>>
        get() = _specieLiveData

    init {
        _characterLiveData.value = Result.Idle
        _planetLiveData.value = Result.Idle
        _specieLiveData.value = Result.Idle
        _filmLiveData.value = Result.Idle
    }

    fun getCharacter(url: String) {
        viewModelScope.launch {
            _characterLiveData.value = Result.Loading
            try {
                val character = characterMapper.mapToView(getCharacterInfo(url))
                _characterLiveData.value = Result.Success(character)
            } catch (e: Exception) {
                _characterLiveData.value = Result.Error(e.message)
            }
        }
    }

    fun getFilms(urls: List<String>) {
        viewModelScope.launch {
            _filmLiveData.value = Result.Loading
            try {
                val films = filmMapper.mapToViewList(getFilmInfo(urls))
                _filmLiveData.value = Result.Success(films)
            } catch (e: Exception) {
                _filmLiveData.value = Result.Error(e.message)
            }
        }
    }

    fun getHomeWorld(url: String) {
        viewModelScope.launch {
            _planetLiveData.value = Result.Loading
            try {
                val planet = planetMapper.mapToView(getPlanetInfo(url))
                _planetLiveData.value = Result.Success(planet)
            } catch (e: Exception) {
                _planetLiveData.value = Result.Error(e.message)
            }
        }
    }

    fun getSpecies(urls: List<String>) {
        viewModelScope.launch {
            _specieLiveData.value = Result.Loading
            try {
                val species = specieMapper.mapToViewList(getSpecieInfo(urls))
                _specieLiveData.value = Result.Success(species)
            } catch (e: Exception) {
                _specieLiveData.value = Result.Error(e.message)
            }
        }
    }

}