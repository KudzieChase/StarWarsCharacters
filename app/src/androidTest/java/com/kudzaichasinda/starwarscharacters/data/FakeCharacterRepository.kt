package com.kudzaichasinda.starwarscharacters.data

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository

class FakeCharacterRepository : CharacterRepository {
    override suspend fun getCharacterInfo(url: String): Character {
        return FakeData.fakeCharacter
    }

    override suspend fun getSpecieInfo(urls: List<String>): List<Specie> {
        return FakeData.fakeSpecies
    }

    override suspend fun getPlanetInfo(url: String): Planet {
        return FakeData.fakePlanet
    }

    override suspend fun getFilmInfo(urls: List<String>): List<Film> {
        return FakeData.fakeFilms
    }
}
