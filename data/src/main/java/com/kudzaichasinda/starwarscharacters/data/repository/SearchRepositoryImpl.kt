package com.kudzaichasinda.starwarscharacters.data.repository

import com.kudzaichasinda.starwarscharacters.data.mapper.CharacterEntityMapper
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val mapper: CharacterEntityMapper
) : SearchRepository {

    override fun searchCharacter(name: String): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.searchCharacter(characterName = name)
            val characters = mapper.mapToDomainList(list = response.results)
            emit(Resource.Success(data = characters))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Failed Due to Connection Error"))
        }
    }
}
