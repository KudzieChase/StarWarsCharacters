package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.UseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Planet
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetPlanetInfo @Inject constructor(
    private val repository: CharacterRepository
) : UseCase<Planet, String>() {
    override suspend operator fun invoke(params: String): Planet = repository.getPlanetInfo(params)
}
