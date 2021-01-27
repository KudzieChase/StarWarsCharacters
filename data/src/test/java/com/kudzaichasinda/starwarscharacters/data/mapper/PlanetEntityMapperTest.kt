package com.kudzaichasinda.starwarscharacters.data.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.mapper.data.FakeData
import org.junit.Test

class PlanetEntityMapperTest {

    private val mapper = PlanetEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakePlanet = FakeData.fakePlanet

        val domainPlanet = mapper.mapToDomain(fakePlanet)

        assertThat(domainPlanet.name).isEqualTo(fakePlanet.name)
        assertThat(domainPlanet.population).isEqualTo(fakePlanet.population)
    }
}