package com.kudzaichasinda.starwarscharacters.mapper

import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.mapper.base.Mapper
import com.kudzaichasinda.starwarscharacters.model.SpecieView
import javax.inject.Inject

class SpecieViewMapper @Inject constructor() : Mapper<SpecieView, Specie> {
    override fun mapToView(domain: Specie): SpecieView {
        return SpecieView(
            name = domain.name,
            language = domain.language,
            homeWorld = domain.homeWorld
        )
    }
}
