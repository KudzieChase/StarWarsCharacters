package com.kudzaichasinda.starwarscharacters.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.mapper.data.FakeData
import org.junit.Test

class CharacterViewMapperTest {

    private val mapper = CharacterViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val fakeCharacter = FakeData.fakeCharacter

        val characterView = mapper.mapToView(fakeCharacter)

        assertThat(characterView.name).isEqualTo(fakeCharacter.name)
        assertThat(characterView.height).isEqualTo(fakeCharacter.height)
        assertThat(characterView.birthYear).isEqualTo(fakeCharacter.birthYear)
        assertThat(characterView.films).isEqualTo(fakeCharacter.films)
        assertThat(characterView.species).isEqualTo(fakeCharacter.species)
        assertThat(characterView.homeWorld).isEqualTo(fakeCharacter.homeWorld)
        assertThat(characterView.url).isEqualTo(fakeCharacter.url)
    }

    @Test
    fun `checks that mapToViewList maps a domain model list to view model list`() {
        val fakeCharacters = listOf(FakeData.fakeCharacter)

        val characters = mapper.mapToViewList(fakeCharacters)

        assertThat(characters.size).isEqualTo(fakeCharacters.size)
        assertThat(characters[0].name).isEqualTo(fakeCharacters[0].name)
    }
}
