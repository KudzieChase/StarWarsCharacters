package com.kudzaichasinda.starwarscharacters.domain.interactor.base

/**
 * Base class for generic use case
 * */
abstract class UseCase<T, in Params> {
    abstract suspend operator fun invoke(params: Params): T
}
