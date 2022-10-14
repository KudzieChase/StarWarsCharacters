package com.kudzaichasinda.starwarscharacters.mvi

interface Middleware<S : ViewState, A : Action> {
    suspend fun process(
        action: A,
        currentState: S,
        store: Store<S, A>
    )
}
