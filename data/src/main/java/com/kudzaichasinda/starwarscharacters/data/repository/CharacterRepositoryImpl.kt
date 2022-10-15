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
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val characterMapper: CharacterEntityMapper,
    private val specieMapper: SpecieEntityMapper,
    private val planetMapper: PlanetEntityMapper,
    private val filmMapper: FilmEntityMapper
) : CharacterRepository {

    override fun getCharacterInfo(url: String): Flow<Resource<Character>> = flow {
        emit(Resource.Loading)

        try {
            val response = service.getCharacterInfo(url = url)
            val character = characterMapper.mapToDomain(entity = response)
            emit(Resource.Success(data = character))
        } catch (ex: HttpException) {
            emit(Resource.Error(message = ex.message()))
        }
    }

    override fun getSpecieInfo(urls: List<String>): Flow<Resource<List<Specie>>> = flow {
        emit(Resource.Loading)

        val species: List<SpecieEntity> = urls.map {
            service.getSpecieInfo(it)
        }

        // Grab home world planet name from planet to create a displayable species
        val displayableSpecies = mutableListOf<SpecieEntity>()
        species.forEach { specie ->
            try {
                val homeWorld = specie.homeWorld?.let {
                    service.getPlanetInfo(it)
                }
                displayableSpecies.add(
                    specie.copy(homeWorld = homeWorld?.name ?: "")
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "Error occurred fetching specie info"))
            }
        }

        emit(Resource.Success(specieMapper.mapToDomainList(list = displayableSpecies)))
    }

    override fun getPlanetInfo(url: String): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading)

        try {
            val response = service.getPlanetInfo(url = url)
            val planet = planetMapper.mapToDomain(entity = response)
            emit(Resource.Success(data = planet))
        } catch (ex: HttpException) {
            emit(Resource.Error(message = ex.message()))
        }
    }

    override fun getFilmInfo(urls: List<String>): Flow<Resource<List<Film>>> = flow {
        emit(Resource.Loading)

        try {
            val films: List<FilmEntity> = urls.map {
                service.getFilmInfo(it)
            }
            emit(Resource.Success(data = filmMapper.mapToDomainList(list = films)))
        } catch (ex: HttpException) {
            emit(Resource.Error(message = ex.message()))
        }
    }
}
