package com.kudzaichasinda.starwarscharacters.data.mapper

import com.kudzaichasinda.starwarscharacters.data.mapper.base.DomainMapper
import com.kudzaichasinda.starwarscharacters.data.model.SpecieEntity
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import javax.inject.Inject

class SpecieEntityMapper @Inject constructor() : DomainMapper<SpecieEntity, Specie> {

    override fun mapToDomain(entity: SpecieEntity): Specie {
        return Specie(
            name = entity.name,
            homeWorld = entity.homeWorld,
            language = entity.language
        )
    }
}