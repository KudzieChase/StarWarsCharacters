package com.kudzaichasinda.starwarscharacters.data.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.mapper.data.FakeData
import org.junit.Test

class SpecieEntityMapperTest {

    private val mapper = SpecieEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakeSpecie = FakeData.fakeSpecie

        val domainSpecie = mapper.mapToDomain(fakeSpecie)

        assertThat(domainSpecie.name).isEqualTo(fakeSpecie.name)
        assertThat(domainSpecie.language).isEqualTo(fakeSpecie.language)
        assertThat(domainSpecie.homeWorld).isEqualTo(fakeSpecie.homeWorld)
    }

    @Test
    fun `checks that mapToDomainList maps entity list to domain list`() {
        val entityList = FakeData.fakeSpecies

        val domainSpecies = mapper.mapToDomainList(entityList)

        assertThat(domainSpecies.size).isEqualTo(entityList.size)
        assertThat(domainSpecies[0].name).isEqualTo(entityList[0].name)
    }
}
