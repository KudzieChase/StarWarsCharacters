package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.UseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterInfo @Inject constructor(
    private val repository: CharacterRepository
) : UseCase<Character, String>() {
    override suspend operator fun invoke(params: String): Character =
        repository.getCharacterInfo(params)
}
