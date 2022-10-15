package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.FlowUseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecieInfo @Inject constructor(
    private val repository: CharacterRepository
) : FlowUseCase<Resource<List<Specie>>, List<String>>() {
    override operator fun invoke(params: List<String>?): Flow<Resource<List<Specie>>> {
        if (params == null) throw IllegalArgumentException("Params cannot be empty")
        return repository.getSpecieInfo(params)
    }
}
