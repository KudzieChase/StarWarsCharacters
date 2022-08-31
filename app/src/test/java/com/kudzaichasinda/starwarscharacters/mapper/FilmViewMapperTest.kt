package com.kudzaichasinda.starwarscharacters.mapper

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.mapper.data.FakeData
import org.junit.Test

class FilmViewMapperTest {
    private val mapper = FilmViewMapper()

    @Test
    fun `checks that mapToView maps domain model to view model`() {
        val fakeFilm = FakeData.fakeFilm

        val filmView = mapper.mapToView(fakeFilm)

        assertThat(filmView.title).isEqualTo(fakeFilm.title)
        assertThat(filmView.openingCrawl).isEqualTo(fakeFilm.openingCrawl)
    }

    @Test
    fun `checks that mapToViewList maps a domain model list to view model list`() {
        val fakeFilms = FakeData.fakeFilms

        val films = mapper.mapToViewList(fakeFilms)
        assertThat(films.size).isEqualTo(fakeFilms.size)
    }
}
