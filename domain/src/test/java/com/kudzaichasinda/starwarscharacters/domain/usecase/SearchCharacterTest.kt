package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchCharacterTest {

    private lateinit var searchCharacter: SearchCharacter

    @Mock
    lateinit var searchRepository: SearchRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchCharacter = SearchCharacter(repository = searchRepository)
    }

    private fun stubSearchRepositorySearchCharacter(results: Flow<List<Character>>) {
        whenever(searchCharacter(FakeData.characterName))
            .thenReturn(results)
    }

    private fun stubSearchRepositorySearchCharacterNotFound() {
        whenever((searchCharacter("Grogu")))
            .thenReturn(flowOf(listOf()))
    }

    @Test
    fun `checks that SearchCharacter returns a list of characters`() = runBlockingTest {
        stubSearchRepositorySearchCharacter(flowOf(listOf(FakeData.fakeCharacter)))

        val characters = searchCharacter(FakeData.characterName).first()
        assertThat(characters.size).isAtLeast(1)
    }

    @Test
    fun `checks that SearchCharacter should be empty when Character is not there`() =
        runBlockingTest {
            stubSearchRepositorySearchCharacterNotFound()

            val characters = searchCharacter("Grogu").first()

            assertThat(characters.size).isAtMost(0)
        }

    @Test
    fun `checks that SearchCharacter actually gives us the correct Character`() =
        runBlockingTest {
            stubSearchRepositorySearchCharacter(flowOf(listOf(FakeData.fakeCharacter)))

            val characters = searchCharacter(FakeData.characterName).first()

            assertThat(characters.size).isAtLeast(1)
            val character = characters[0]

            assertThat(character).isEqualTo(FakeData.fakeCharacter)
        }

    @Test(expected = IllegalArgumentException::class)
    fun `checks that SearchCharacter throws IllegalArgumentException when params is null`() =
        runBlockingTest {
            searchCharacter().first()
        }
}