package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.FlowUseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.model.Resource
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmInfo @Inject constructor(
    private val repository: CharacterRepository
) : FlowUseCase<Resource<List<Film>>, List<String>>() {
    override operator fun invoke(params: List<String>?): Flow<Resource<List<Film>>> {
        if (params == null) throw IllegalArgumentException("Params for Film are null")
        return repository.getFilmInfo(urls = params)
    }
}
