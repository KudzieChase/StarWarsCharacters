package com.kudzaichasinda.starwarscharacters.mapper.data

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Specie

/**
 * An object with some fake data for testing
 * */
internal object FakeData {

    val fakeCharacter = Character(
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
        Film(
            title = "Revenge of the Sith",
            openingCrawl = "I remember Obi Wan Kenobi having the high ground"
        ),
        Film(
            title = "Empire Strikes Back",
            openingCrawl = "I am your father Luke? Maybe?"
        )
    )

    val fakeFilm = Film(
        title = "Revenge of the Sith",
        openingCrawl = "I remember Obi Wan Kenobi having the high ground"
    )

    val fakePlanet = Planet(
        name = "Tatooine",
        population = "100000"
    )

    val fakeSpecies = listOf(
        Specie(
            name = "Human",
            language = "Basic",
            homeWorld = "http://url/api/planets/9/"
        )
    )

    val fakeSpecie = Specie(
        name = "Human",
        language = "Basic",
        homeWorld = "http://url/api/planets/9/"
    )
}