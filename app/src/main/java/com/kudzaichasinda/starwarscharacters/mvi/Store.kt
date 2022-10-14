package com.kudzaichasinda.starwarscharacters.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Our [Store] is our state container for any given screen
 *
 * @param [initialState] This is the initial state of the screen when it is first created
 * @param [reducer] A system for taking in the current state and a new action, and output the
 * updated state
 * @param [middlewares] This is a list of [Middleware] entities for handling any side effects
 * for actions dispatched to this store.
 */
class Store<S : ViewState, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList()
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val currentState: S
        get() = _state.value

    suspend fun dispatch(intent: A) {
        middlewares.forEach { middleware ->
            middleware.process(intent, currentState, this)
        }

        val newState = reducer.reduce(currentState = currentState, action = intent)
        _state.value = newState
    }
}
