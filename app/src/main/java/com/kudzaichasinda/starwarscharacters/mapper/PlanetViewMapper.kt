package com.kudzaichasinda.starwarscharacters.mapper

import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.mapper.base.Mapper
import com.kudzaichasinda.starwarscharacters.model.PlanetView
import javax.inject.Inject

class PlanetViewMapper @Inject constructor() : Mapper<PlanetView, Planet> {
    override fun mapToView(domain: Planet): PlanetView {
        return PlanetView(
            name = domain.name,
            population = domain.population
        )
    }
}