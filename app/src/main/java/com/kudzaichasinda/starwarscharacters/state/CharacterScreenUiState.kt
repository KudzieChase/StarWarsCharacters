package com.kudzaichasinda.starwarscharacters.state

import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.model.FilmView
import com.kudzaichasinda.starwarscharacters.model.PlanetView
import com.kudzaichasinda.starwarscharacters.model.SpecieView

data class CharacterViewUiState(
    val character: CharacterView? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val loading: CharacterViewUiState
        get() = copy(isLoading = true)

    fun success(character: CharacterView): CharacterViewUiState =
        copy(character = character, isLoading = false)

    fun hasError(error: String): CharacterViewUiState =
        copy(error = error, isLoading = false)
}

data class FilmsViewUiState(
    val films: List<FilmView> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val loading: FilmsViewUiState
        get() = copy(isLoading = true)

    fun success(films: List<FilmView>): FilmsViewUiState =
        copy(films = films, isLoading = false)

    fun hasError(error: String): FilmsViewUiState =
        copy(error = error, isLoading = false)
}

data class PlanetViewUiState(
    val planet: PlanetView? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val loading: PlanetViewUiState
        get() = copy(isLoading = true)

    fun success(planet: PlanetView): PlanetViewUiState =
        copy(planet = planet, isLoading = false)

    fun hasError(error: String): PlanetViewUiState =
        copy(error = error, isLoading = false)
}

data class SpeciesViewUiState(
    val species: List<SpecieView> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val loading: SpeciesViewUiState
        get() = copy(isLoading = true)

    fun success(species: List<SpecieView>): SpeciesViewUiState =
        copy(species = species, isLoading = false)

    fun hasError(error: String): SpeciesViewUiState =
        copy(error = error, isLoading = false)
}

data class CharacterScreenUiState(
    val characterUiState: CharacterViewUiState = CharacterViewUiState(),
    val filmsUiState: FilmsViewUiState = FilmsViewUiState(),
    val planetUiState: PlanetViewUiState = PlanetViewUiState(),
    val speciesViewUiState: SpeciesViewUiState = SpeciesViewUiState()
) {
    fun characterState(state: CharacterViewUiState): CharacterScreenUiState =
        copy(characterUiState = state)

    fun filmsState(state: FilmsViewUiState): CharacterScreenUiState =
        copy(filmsUiState = state)

    fun planetState(state: PlanetViewUiState): CharacterScreenUiState =
        copy(planetUiState = state)

    fun speciesState(state: SpeciesViewUiState): CharacterScreenUiState =
        copy(speciesViewUiState = state)
}
