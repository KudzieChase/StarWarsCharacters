package com.kudzaichasinda.starwarscharacters.mapper

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.mapper.base.Mapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import javax.inject.Inject

class CharacterViewMapper @Inject constructor() : Mapper<CharacterView, Character> {
    override fun mapToView(domain: Character): CharacterView {
        return CharacterView(
            name = domain.name,
            height = domain.height,
            birthYear = domain.birthYear,
            films = domain.films,
            species = domain.species,
            homeWorld = domain.homeWorld,
            url = domain.url
        )
    }
}
