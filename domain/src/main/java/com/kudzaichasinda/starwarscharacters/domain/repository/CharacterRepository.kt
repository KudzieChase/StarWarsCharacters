package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterInfo(url: String): Flow<Resource<Character>>

    fun getSpecieInfo(urls: List<String>): Flow<Resource<List<Specie>>>

    fun getPlanetInfo(url: String): Flow<Resource<Planet>>

    fun getFilmInfo(urls: List<String>): Flow<Resource<List<Film>>>
}
