package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetSpecieInfo
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
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
class GetSpecieInfoTest {

    private lateinit var getSpecieInfo: GetSpecieInfo

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getSpecieInfo = GetSpecieInfo(repository = characterRepository)
    }

    private suspend fun stubCharacterRepositoryGetSpecieInfo(species: List<Specie>) {
        whenever(getSpecieInfo(FakeData.fakeCharacter.species))
            .thenReturn(species)
    }

    @Test
    fun `checks that GetSpecieInfo returns a list of films`() = runBlockingTest {
        stubCharacterRepositoryGetSpecieInfo(FakeData.fakeSpecies)

        val species = getSpecieInfo(FakeData.fakeCharacter.species)

        assertThat(species.size).isEqualTo(1)

        assertThat(species).isEqualTo(FakeData.fakeSpecies)
    }
}