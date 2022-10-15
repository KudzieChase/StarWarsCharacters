package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.FlowUseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetInfo @Inject constructor(
    private val repository: CharacterRepository
) : FlowUseCase<Resource<Planet>, String>() {
    override operator fun invoke(params: String?): Flow<Resource<Planet>> {
        if (params == null) throw IllegalArgumentException("params cannot be null")
        return repository.getPlanetInfo(params)
    }
}
