package com.kudzaichasinda.starwarscharacters.data.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.mapper.data.FakeData
import org.junit.Test

class CharacterEntityMapperTest {

    private val mapper = CharacterEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakeCharacterEntity = FakeData.fakeCharacter

        val domainCharacter = mapper.mapToDomain(fakeCharacterEntity)

        assertThat(domainCharacter.name).isEqualTo(fakeCharacterEntity.name)
        assertThat(domainCharacter.height).isEqualTo(fakeCharacterEntity.height)
        assertThat(domainCharacter.birthYear).isEqualTo(fakeCharacterEntity.birthYear)
        assertThat(domainCharacter.films).isEqualTo(fakeCharacterEntity.films)
        assertThat(domainCharacter.species).isEqualTo(fakeCharacterEntity.species)
        assertThat(domainCharacter.homeWorld).isEqualTo(fakeCharacterEntity.homeWorld)
        assertThat(domainCharacter.url).isEqualTo(fakeCharacterEntity.url)
    }
}
