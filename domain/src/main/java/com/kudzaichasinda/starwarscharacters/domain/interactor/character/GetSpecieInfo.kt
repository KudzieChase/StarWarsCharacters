package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.UseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Specie
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetSpecieInfo @Inject constructor(
    private val repository: CharacterRepository
) : UseCase<List<Specie>, List<String>>() {
    override suspend operator fun invoke(params: List<String>): List<Specie> =
        repository.getSpecieInfo(params)
}