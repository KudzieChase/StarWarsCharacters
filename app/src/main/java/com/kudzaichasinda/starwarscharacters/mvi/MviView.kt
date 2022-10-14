package com.kudzaichasinda.starwarscharacters.mvi

import kotlinx.coroutines.flow.Flow

interface MviView<A : Action, out S : ViewState> {
    fun processIntent(intent: A)
    fun state(): Flow<S>
}
