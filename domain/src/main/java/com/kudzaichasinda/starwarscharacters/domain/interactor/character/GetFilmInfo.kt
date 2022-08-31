package com.kudzaichasinda.starwarscharacters.domain.interactor.character

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.UseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Film
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetFilmInfo @Inject constructor(
    private val repository: CharacterRepository
) : UseCase<List<Film>, List<String>>() {
    override suspend operator fun invoke(params: List<String>): List<Film> =
        repository.getFilmInfo(params)
}
