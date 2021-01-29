package com.kudzaichasinda.starwarscharacters.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.mapper.data.FakeData
import org.junit.Test

class PlanetViewMapperTest {

    private val mapper = PlanetViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val fakePlanet = FakeData.fakePlanet

        val planetView = mapper.mapToView(fakePlanet)

        assertThat(planetView.name).isEqualTo(fakePlanet.name)
        assertThat(planetView.population).isEqualTo(fakePlanet.population)
    }
}