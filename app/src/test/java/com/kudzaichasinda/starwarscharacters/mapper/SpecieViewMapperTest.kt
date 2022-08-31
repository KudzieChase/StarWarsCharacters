package com.kudzaichasinda.starwarscharacters.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.mapper.data.FakeData
import org.junit.Test

class SpecieViewMapperTest {

    private val mapper = SpecieViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val fakeSpecie = FakeData.fakeSpecie

        val specieView = mapper.mapToView(fakeSpecie)

        assertThat(specieView.name).isEqualTo(fakeSpecie.name)
        assertThat(specieView.language).isEqualTo(fakeSpecie.language)
        assertThat(specieView.homeWorld).isEqualTo(fakeSpecie.homeWorld)
    }

    @Test
    fun `checks that mapToViewList maps a domain model list to view model list`() {
        val fakeSpecies = FakeData.fakeSpecies

        val species = mapper.mapToViewList(fakeSpecies)
        assertThat(species.size).isEqualTo(fakeSpecies.size)
    }
}
