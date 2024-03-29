package com.kudzaichasinda.starwarscharacters.domain.interactor.search

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.FlowUseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchCharacter @Inject constructor(
    private val repository: SearchRepository
) : FlowUseCase<Resource<List<Character>>, String>() {
    override operator fun invoke(params: String?): Flow<Resource<List<Character>>> {
        if (params == null) throw IllegalArgumentException("Parameters for SearchCharacter cannot be null")
        return repository.searchCharacter(params).flowOn(Dispatchers.IO)
    }
}
