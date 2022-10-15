package com.kudzaichasinda.starwarscharacters.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kudzaichasinda.starwarscharacters.data.FakeCharacterRepository
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetCharacterInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetFilmInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetPlanetInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetSpecieInfo
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.FilmViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.PlanetViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.SpecieViewMapper
import com.kudzaichasinda.starwarscharacters.ui.character.CharacterViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CharacterRepository
    private lateinit var getCharacterInfo: GetCharacterInfo
    private lateinit var getFilmInfo: GetFilmInfo
    private lateinit var getPlanetInfo: GetPlanetInfo
    private lateinit var getSpecieInfo: GetSpecieInfo

    private lateinit var viewModel: CharacterViewModel

    private val characterMapper = CharacterViewMapper()
    private val filmMapper = FilmViewMapper()
    private val planetMapper = PlanetViewMapper()
    private val specieMapper = SpecieViewMapper()

    @Before
    fun setup() {
        repository = FakeCharacterRepository()
        getCharacterInfo = GetCharacterInfo(repository)
        getFilmInfo = GetFilmInfo(repository)
        getPlanetInfo = GetPlanetInfo(repository)
        getSpecieInfo = GetSpecieInfo(repository)

        viewModel = CharacterViewModel(
            getCharacterInfo,
            getFilmInfo,
            getPlanetInfo,
            getSpecieInfo,
            characterMapper,
            specieMapper,
            filmMapper,
            planetMapper
        )
    }

    @Test
    fun getCharacter_returnsCharacter() {
    }

    @Test
    fun getFilms_returnsFilms() {
    }

    @Test
    fun getHomeWorld_returnsPlanet() {
    }

    @Test
    fun getSpecies_returnsSpecies() {
    }
}
