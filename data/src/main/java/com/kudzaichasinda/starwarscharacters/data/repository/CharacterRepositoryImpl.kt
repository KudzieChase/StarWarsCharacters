package com.kudzaichasinda.starwarscharacters.data.repository

import com.kudzaichasinda.starwarscharacters.data.mapper.CharacterEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.FilmEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.PlanetEntityMapper
import com.kudzaichasinda.starwarscharacters.data.mapper.SpecieEntityMapper
import com.kudzaichasinda.starwarscharacters.data.model.FilmEntity
import com.kudzaichasinda.starwarscharacters.data.model.SpecieEntity
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import java.lang.Exception
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val characterMapper: CharacterEntityMapper,
    private val specieMapper: SpecieEntityMapper,
    private val planetMapper: PlanetEntityMapper,
    private val filmMapper: FilmEntityMapper
) : CharacterRepository {

    override suspend fun getCharacterInfo(url: String): Character {
        return characterMapper.mapToDomain(service.getCharacterInfo(url))
    }

    override suspend fun getSpecieInfo(urls: List<String>): List<Specie> {
        val species: List<SpecieEntity> = urls.map {
            service.getSpecieInfo(it)
        }

        //Grab homeworld planet name from planet to create a displayable species
        val displayableSpecies = mutableListOf<SpecieEntity>()
        species.forEach { specie ->
            try {
                val homeWorld = specie.homeWorld?.let { service.getPlanetInfo(it) }

                displayableSpecies.add(
                    specie.copy(homeWorld = homeWorld?.name ?: "")
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return specieMapper.mapToDomainList(displayableSpecies)
    }

    override suspend fun getPlanetInfo(url: String): Planet {
        return planetMapper.mapToDomain(service.getPlanetInfo(url))
    }

    override suspend fun getFilmInfo(urls: List<String>): List<Film> {
        val films: List<FilmEntity> = urls.map {
            service.getFilmInfo(it)
        }
        return filmMapper.mapToDomainList(films)
    }
}