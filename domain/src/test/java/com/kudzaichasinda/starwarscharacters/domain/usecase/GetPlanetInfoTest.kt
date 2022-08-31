package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetPlanetInfo
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
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
class GetPlanetInfoTest {

    private lateinit var getPlanetInfo: GetPlanetInfo

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPlanetInfo = GetPlanetInfo(repository = characterRepository)
    }

    private suspend fun stubCharacterRepositoryGetPlanetInfo(planet: Planet) {
        whenever(getPlanetInfo(FakeData.fakeCharacter.homeWorld))
            .thenReturn(planet)
    }

    @Test
    fun `checks that GetPlanetInfo can actually fetch from repository`() = runBlockingTest {
        stubCharacterRepositoryGetPlanetInfo(FakeData.fakePlanet)

        val planet = getPlanetInfo(FakeData.fakeCharacter.homeWorld)

        assertThat(planet).isEqualTo(FakeData.fakePlanet)
    }
}
