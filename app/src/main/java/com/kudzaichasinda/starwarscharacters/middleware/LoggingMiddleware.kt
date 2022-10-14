package com.kudzaichasinda.starwarscharacters.middleware

import android.util.Log
import com.kudzaichasinda.starwarscharacters.mvi.Action
import com.kudzaichasinda.starwarscharacters.mvi.Middleware
import com.kudzaichasinda.starwarscharacters.mvi.Store
import com.kudzaichasinda.starwarscharacters.mvi.ViewState

class LoggingMiddleware<S : ViewState, A : Action> : Middleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v(
            "LoggingMiddleware",
            "Processing action: $action; Current State: $currentState"
        )
    }
}
