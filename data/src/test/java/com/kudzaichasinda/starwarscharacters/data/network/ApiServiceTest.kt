package com.kudzaichasinda.starwarscharacters.data.network

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.mapper.CharacterEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.FilmEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.PlanetEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.SpecieEntityMapper
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.data.repository.CharacterRepositoryImpl
import com.kudzaichasinda.starwarscharacters.data.repository.SearchRepositoryImpl
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var characterRepository: CharacterRepository
    private lateinit var searchRepository: SearchRepository

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = ServiceDispatcher()

        characterRepository = CharacterRepositoryImpl(
            getApiService(),
            CharacterEntityMapper(),
            SpecieEntityMapper(),
            PlanetEntityMapper(),
            FilmEntityMapper()
        )

        searchRepository = SearchRepositoryImpl(
            getApiService(),
            CharacterEntityMapper()
        )
    }

    /**
     * Helper function to get our API service
     * */
    private fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Test
    fun `checks that getCharacterInfo response returns correctly`() = runBlocking {
        val character = characterRepository.getCharacterInfo(Constants.CHARACTER_URL)

        assertThat(character).isNotNull()

        assertThat(character.birthYear).isEqualTo("19BBY")
        assertThat(character.species).isEmpty()
        assertThat(character.films).isNotEmpty()
        assertThat(character.name).isEqualTo("Luke Skywalker")
    }

    @Test
    fun `checks that getSpecieInfo response can be parsed and returns correctly`() = runBlocking {
        val species = characterRepository.getSpecieInfo(listOf(Constants.SPECIE_URL))

        assertThat(species).isNotNull()

        assertThat(species[0].name).isEqualTo("Human")
        assertThat(species[0].homeWorld).isEqualTo("Tatooine")
        assertThat(species[0].language).isEqualTo("Galactic Basic")
    }

    @Test
    fun `checks that getPlanetInfo response be parsed and returns correctly`() = runBlocking {
        val planet = characterRepository.getPlanetInfo(Constants.PLANETS_URL)

        assertThat(planet).isNotNull()

        assertThat(planet.name).isEqualTo("Tatooine")
        assertThat(planet.population).isEqualTo("200000")
    }

    @Test
    fun `checks that getFilmInfo response can be parsed and returns correctly`() = runBlocking {
        val films = characterRepository.getFilmInfo(listOf(Constants.FILMS_URL))

        assertThat(films).isNotNull()

        assertThat(films[0].title).isEqualTo("A New Hope")
        assertThat(films[0].openingCrawl).isEqualTo("It is a period of civil war")
    }

    @Test
    fun `checks that getSpecieInfo response returns empty list when supplied with empty urls `() =
        runBlocking {
            val species = characterRepository.getSpecieInfo(listOf())

            assertThat(species).isEmpty()
        }

    @Test
    fun `checks that getFilmInfo response returns empty list when supplied with empty urls`() =
        runBlocking {
            val films = characterRepository.getFilmInfo(listOf())

            assertThat(films).isEmpty()
        }

    @Test
    fun `checks that searchCharacter can be parsed and returns correctly`() = runBlocking {
        val characters = searchRepository.searchCharacter(Constants.SEARCH_QUERY)

        assertThat(characters).isNotNull()

        assertThat(characters.first()).isNotEmpty()
        assertThat(characters.first().size).isAtLeast(2)

        characters.collect { character ->
            assertThat(character[0].name).isEqualTo("Luke Skywalker")
            assertThat(character[1].name).isEqualTo("Luminara Unduli")
        }
    }

    @Test
    fun `checks that searchCharacter has empty results when search query is not found`() =
        runBlocking {
            val characters = searchRepository.searchCharacter(Constants.SEARCH_QUERY_FAIL)

            assertThat(characters).isNotNull()
            assertThat(characters.first()).isEmpty()
        }

    @Test(expected = HttpException::class)
    fun `checks that error is returned for urls that are not found`() = runBlocking {
        val character = characterRepository.getCharacterInfo(Constants.URL_NOT_FOUND)

        assertThat(character).isNull()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
