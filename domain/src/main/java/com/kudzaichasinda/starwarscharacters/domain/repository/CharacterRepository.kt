package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Specie

interface CharacterRepository {

    suspend fun getCharacterInfo(url: String): Character

    suspend fun getSpecieInfo(urls: List<String>): List<Specie>

    suspend fun getPlanetInfo(url: String): Planet

    suspend fun getFilmInfo(urls: List<String>): List<Film>
}
