package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.FlowUseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Character
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterInfo @Inject constructor(
    private val repository: CharacterRepository
) : FlowUseCase<Resource<Character>, String>() {
    override operator fun invoke(params: String?): Flow<Resource<Character>> {
        if (params == null) throw IllegalArgumentException("Params for Get Character Info are null")
        return repository.getCharacterInfo(url = params)
    }
}
