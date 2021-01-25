package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetFilmInfo
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFilmInfoTest {

    private lateinit var getFilmInfo: GetFilmInfo

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getFilmInfo = GetFilmInfo(repository = characterRepository)
    }

    private suspend fun stubCharacterRepositoryGetFilmInfo(films: List<Film>) {
        whenever(getFilmInfo(FakeData.fakeCharacter.films))
            .thenReturn(films)
    }

    @Test
    fun `checks that GetFilmInfo returns a list of films`() = runBlockingTest {
        stubCharacterRepositoryGetFilmInfo(FakeData.fakeFilms)

        val films = getFilmInfo(FakeData.fakeCharacter.films)

        assertThat(films.size).isAtLeast(2)

        assertThat(films).isEqualTo(FakeData.fakeFilms)
    }

}