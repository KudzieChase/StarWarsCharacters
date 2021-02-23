package com.kudzaichasinda.starwarscharacters.data.mapper

import com.kudzaichasinda.starwarscharacters.data.mapper.base.DomainMapper
import com.kudzaichasinda.starwarscharacters.data.model.CharacterEntity
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() : DomainMapper<CharacterEntity, Character> {

    override fun mapToDomain(entity: CharacterEntity): Character {
        return Character(
            name = entity.name,
            height = entity.height,
            birthYear = entity.birthYear,
            films = entity.films,
            species = entity.species,
            homeWorld = entity.homeWorld,
            url = entity.url
        )
    }
}