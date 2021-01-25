package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.character.GetCharacterInfo
import com.kudzaichasinda.starwarscharacters.domain.model.Character
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
class GetCharacterInfoTest {

    private lateinit var getCharacterInfo: GetCharacterInfo

    @Mock
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCharacterInfo = GetCharacterInfo(repository = characterRepository)
    }

    private suspend fun stubCharacterRepositoryGetCharacterInfo(character: Character) {
        whenever(getCharacterInfo(FakeData.characterUrl))
            .thenReturn(character)
    }

    @Test
    fun `checks that GetCharacterInfo can actually fetch from repository`() = runBlockingTest {
        stubCharacterRepositoryGetCharacterInfo(FakeData.fakeCharacter)

        val character = getCharacterInfo(FakeData.characterUrl)

        assertThat(character).isEqualTo(FakeData.fakeCharacter)
    }


}