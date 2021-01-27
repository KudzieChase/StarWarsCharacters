package com.kudzaichasinda.starwarscharacters.data.mapper.data

import com.kudzaichasinda.starwarscharacters.data.model.CharacterEntity
import com.kudzaichasinda.starwarscharacters.data.model.FilmEntity
import com.kudzaichasinda.starwarscharacters.data.model.PlanetEntity
import com.kudzaichasinda.starwarscharacters.data.model.SpecieEntity

/**
 * An object with some fake data for testing
 * */
internal object FakeData {

    val fakeCharacter = CharacterEntity(
        name = "Luke Skywalker",
        height = "172",
        birthYear = "19BBY",
        films = listOf(
            "https://url/api/films/2/",
            "https://url/api/films/6/",
        ),
        species = listOf(
            "https://url/api/species/2/",
        ),
        homeWorld = "https://url/api/planets/9/",
        url = "https://url/api/people/1/"
    )

    val fakeFilms = listOf(
        FilmEntity(
            title = "Revenge of the Sith",
            openingCrawl = "I remember Obi Wan Kenobi having the high ground"
        ),
        FilmEntity(
            title = "Empire Strikes Back",
            openingCrawl = "I am your father Luke? Maybe?"
        )
    )

    val fakeFilm = FilmEntity(
        title = "Revenge of the Sith",
        openingCrawl = "I remember Obi Wan Kenobi having the high ground"
    )

    val fakePlanet = PlanetEntity(
        name = "Tatooine",
        population = "100000"
    )

    val fakeSpecies = listOf(
        SpecieEntity(
            name = "Human",
            language = "Basic",
            homeWorld = "http://url/api/planets/9/"
        )
    )

    val fakeSpecie = SpecieEntity(
        name = "Human",
        language = "Basic",
        homeWorld = "http://url/api/planets/9/"
    )
}