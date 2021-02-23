package com.kudzaichasinda.starwarscharacters.data.mapper.base

interface EntityMapper<in D, out E> {
    fun mapToEntity(domain: D): E

    fun mapToEntityList(list: List<D>): List<E> = list.mapTo(mutableListOf(), ::mapToEntity)
}