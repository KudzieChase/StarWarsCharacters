package com.kudzaichasinda.starwarscharacters.middleware

import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.intent.SearchIntent
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.mvi.Action
import com.kudzaichasinda.starwarscharacters.mvi.Middleware
import com.kudzaichasinda.starwarscharacters.mvi.Store
import com.kudzaichasinda.starwarscharacters.state.SearchUiViewState
import kotlinx.coroutines.flow.catch
import java.net.UnknownHostException

/**
 * This [Middleware] is responsible for logging every [Action] that is process to the Logcat.
 * So that we can see the flow of our data for debugging purposes.
 */
class SearchMiddleware(
    private val searchCharacter: SearchCharacter,
    private val mapper: CharacterViewMapper
) : Middleware<SearchUiViewState, SearchIntent> {

    override suspend fun process(
        action: SearchIntent,
        currentState: SearchUiViewState,
        store: Store<SearchUiViewState, SearchIntent>
    ) {
        when (action) {
            is SearchIntent.SearchInputChanged ->
                onUserTextChanged(store, action.newText)

            else -> {}
        }
    }

    private suspend fun onUserTextChanged(
        store: Store<SearchUiViewState, SearchIntent>,
        newText: String
    ) {
        store.dispatch(SearchIntent.SearchStarted)
        searchCharacter(params = newText)
            .catch { throwable ->
                if (throwable is UnknownHostException) {
                    store.dispatch(SearchIntent.SearchFailed(Throwable("No Internet Connection")))
                } else {
                    store.dispatch(SearchIntent.SearchFailed(throwable))
                }
            }
            .collect {
                store.dispatch(SearchIntent.SearchSuccess(mapper.mapToViewList(it)))
            }
    }
}
