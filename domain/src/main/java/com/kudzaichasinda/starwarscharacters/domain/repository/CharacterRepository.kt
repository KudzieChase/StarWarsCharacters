package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Specie

interface CharacterRepository {

    suspend fun getCharacterInfo(url: String): Character

    suspend fun getPlanetInfo(url: String): Planet

    suspend fun getSpecieInfo(url: String): Specie

    suspend fun getFilmInfo(url: String): Film

}