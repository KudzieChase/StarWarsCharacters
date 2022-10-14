package com.kudzaichasinda.starwarscharacters.mvi

interface Reducer<S : ViewState, A : Action> {

    /**
     * Given a [currentState] and some [action]. Produce a new [ViewState].
     *
     * This will give us clear and predictable state management, that ensures that each state is
     * associated with some specific user intent or action
     */
    fun reduce(currentState: S, action: A): S
}
