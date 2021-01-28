package com.kudzaichasinda.starwarscharacters.mapper

import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.mapper.base.Mapper
import com.kudzaichasinda.starwarscharacters.model.FilmView
import javax.inject.Inject

class FilmViewMapper @Inject constructor() : Mapper<FilmView, Film> {
    override fun mapToView(domain: Film): FilmView {
        return FilmView(
            title = domain.title,
            openingCrawl = domain.openingCrawl
        )
    }
}