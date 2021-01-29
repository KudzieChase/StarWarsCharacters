package com.kudzaichasinda.starwarscharacters.data

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSearchRepository : SearchRepository {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        return flow {
            emit(listOf(FakeData.fakeCharacter))
        }
    }
}