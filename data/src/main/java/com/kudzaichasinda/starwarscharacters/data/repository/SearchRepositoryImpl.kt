package com.kudzaichasinda.starwarscharacters.data.repository

import com.kudzaichasinda.starwarscharacters.data.mapper.CharacterEntityMapper
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val mapper: CharacterEntityMapper
) : SearchRepository {

    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        return flow {
            emit(mapper.mapToDomainList(service.searchCharacter(name).results))
        }
    }
}
