package com.kudzaichasinda.starwarscharacters.data.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.mapper.data.FakeData
import org.junit.Test

class FilmEntityMapperTest {

    private val mapper = FilmEntityMapper()

    @Test
    fun `checks that mapToDomain maps entity model to domain model`() {
        val fakeFilmEntity = FakeData.fakeFilm

        val domainFilm = mapper.mapToDomain(fakeFilmEntity)

        assertThat(domainFilm.title).isEqualTo(fakeFilmEntity.title)
        assertThat(domainFilm.openingCrawl).isEqualTo(fakeFilmEntity.openingCrawl)
    }

    @Test
    fun `checks that mapToDomainList maps entity list to domain list`() {
        val entityList = FakeData.fakeFilms

        val domainFilms = mapper.mapToDomainList(entityList)

        assertThat(domainFilms.size).isEqualTo(entityList.size)
        assertThat(domainFilms[0].title).isEqualTo(entityList[0].title)
    }
}
