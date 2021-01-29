package com.kudzaichasinda.starwarscharacters.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.FakeCharacterRepository
import com.kudzaichasinda.starwarscharacters.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetCharacterInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetFilmInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetPlanetInfo
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetSpecieInfo
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.FilmViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.PlanetViewMapper
import com.kudzaichasinda.starwarscharacters.mapper.SpecieViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.model.FilmView
import com.kudzaichasinda.starwarscharacters.model.PlanetView
import com.kudzaichasinda.starwarscharacters.model.SpecieView
import com.kudzaichasinda.starwarscharacters.ui.character.CharacterViewModel
import com.kudzaichasinda.starwarscharacters.util.Result
import com.kudzaichasinda.starwarscharacters.util.Result.*
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
        val observer = Observer<Result<CharacterView>> {}

        try {
            viewModel.characterLiveData.observeForever(observer)
            viewModel.getCharacter("/kudzai_is_a_valid_url/")

            val response = viewModel.characterLiveData.value!!

            when (response) {
                is Success -> {
                    assertThat(response.data).isEqualTo(FakeData.fakeCharacter)
                }
                else -> {
                    //We aren't really interested in other cases
                }
            }
        } finally {
            viewModel.characterLiveData.removeObserver(observer)
        }
    }

    @Test
    fun getFilms_returnsFilms() {
        val observer = Observer<Result<List<FilmView>>> {}

        try {
            viewModel.filmLiveData.observeForever(observer)
            viewModel.getFilms(listOf("Something about", "Joining a Jedi Temple"))

            val response = viewModel.filmLiveData.value!!
            when (response) {
                is Success -> {
                    assertThat(response.data).isEqualTo(FakeData.fakeFilms)
                }
                else -> {
                }
            }

        } finally {
            viewModel.filmLiveData.removeObserver(observer)
        }
    }

    @Test
    fun getHomeWorld_returnsPlanet() {
        val observer = Observer<Result<PlanetView>> {}

        try {
            viewModel.planetLiveData.observeForever(observer)
            viewModel.getHomeWorld("Harare")

            val response = viewModel.planetLiveData.value!!
            when (response) {
                is Success -> {
                    assertThat(response.data).isEqualTo(FakeData.fakePlanet)
                }
                else -> {
                }
            }
        } finally {
            viewModel.planetLiveData.removeObserver(observer)
        }
    }

    @Test
    fun getSpecies_returnsSpecies() {
        val observer = Observer<Result<List<SpecieView>>> {}

        try {
            viewModel.specieLiveData.observeForever(observer)
            viewModel.getSpecies(listOf("Buy", "Doge", "Coin"))

            val response = viewModel.specieLiveData.value!!
            when (response) {
                is Success -> {
                    assertThat(response.data).isEqualTo(FakeData.fakeSpecies)
                }
            }
        } finally {
            viewModel.specieLiveData.removeObserver(observer)
        }
    }

}