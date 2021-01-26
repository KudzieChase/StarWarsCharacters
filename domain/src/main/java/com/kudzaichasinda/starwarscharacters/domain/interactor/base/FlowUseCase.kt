package com.kudzaichasinda.starwarscharacters.domain.interactor.base

import kotlinx.coroutines.flow.Flow

/**
 * Base class for Kotlin Flow use case
 * */
abstract class FlowUseCase<T, in Params> {
    abstract suspend operator fun invoke(params: Params? = null): Flow<T>
}