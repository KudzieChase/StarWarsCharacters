package com.kudzaichasinda.starwarscharacters.data.mapper

import com.kudzaichasinda.starwarscharacters.data.mapper.base.DomainMapper
import com.kudzaichasinda.starwarscharacters.data.model.PlanetEntity
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import javax.inject.Inject

class PlanetEntityMapper @Inject constructor() : DomainMapper<PlanetEntity, Planet> {

    override fun mapToDomain(entity: PlanetEntity): Planet {
        return Planet(
            name = entity.name,
            population = entity.population
        )
    }
}