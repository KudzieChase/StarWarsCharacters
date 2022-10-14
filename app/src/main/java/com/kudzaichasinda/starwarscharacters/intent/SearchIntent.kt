package com.kudzaichasinda.starwarscharacters.intent

import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.mvi.Action

/**
 * All the possible actions that can be triggered from the search screen
 */
sealed class SearchIntent : Action {

    data class SearchInputChanged(val newText: String) : SearchIntent()

    // I might not need these as this could be one intent no?
    object SearchInputCleared : SearchIntent()
    object SearchStarted : SearchIntent()
    data class SearchFailed(val error: Throwable?) : SearchIntent()
    data class SearchSuccess(val characterList: List<CharacterView>) : SearchIntent()
}
