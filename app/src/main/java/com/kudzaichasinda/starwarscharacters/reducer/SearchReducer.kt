package com.kudzaichasinda.starwarscharacters.reducer

import android.util.Log
import com.kudzaichasinda.starwarscharacters.intent.SearchIntent
import com.kudzaichasinda.starwarscharacters.mvi.Reducer
import com.kudzaichasinda.starwarscharacters.state.SearchUiViewState

/**
 * Responsible for handling any [SearchIntent], and uses that to create a new [SearchUiViewState]
 */
class SearchReducer : Reducer<SearchUiViewState, SearchIntent> {
    // Notes
    // Avoid using handleXYZ() in returning when statement branches.
    // Even though you may understand it, it may not be understandable to a new person joining the team
    // An example that could be acceptable is stateAfterAction()

    override fun reduce(currentState: SearchUiViewState, action: SearchIntent): SearchUiViewState {
        Log.v("SearchReducer", "Processing action: $action")

        return when (action) {
            is SearchIntent.SearchInputChanged ->
                currentState.copy(
                    searchInputText = action.newText,
                    isIdle = action.newText == ""
                )

            is SearchIntent.SearchFailed -> currentState.copy(
                isLoading = false,
                searchError = "No Results Mr White"
            )

            SearchIntent.SearchInputCleared -> currentState.copy(
                isIdle = true,
                isLoading = false
            )

            SearchIntent.SearchStarted -> currentState.copy(
                isIdle = false,
                isLoading = true
            )

            is SearchIntent.SearchSuccess -> currentState.copy(
                isLoading = false,
                searchResults = action.characterList
            )
        }
    }
}
