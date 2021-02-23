package com.kudzaichasinda.starwarscharacters.data.mapper

import com.kudzaichasinda.starwarscharacters.data.mapper.base.DomainMapper
import com.kudzaichasinda.starwarscharacters.data.model.FilmEntity
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import javax.inject.Inject

class FilmEntityMapper @Inject constructor() : DomainMapper<FilmEntity, Film> {

    override fun mapToDomain(entity: FilmEntity): Film {
        return Film(
            title = entity.title,
            openingCrawl = entity.openingCrawl
        )
    }
}