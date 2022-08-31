package com.kudzaichasinda.starwarscharacters.data.mapper.base

interface ResponseMapper<in E, out D> {
    fun mapToDomain(entity: E): D

    fun mapToDomainList(list: List<E>): List<D> = list.mapTo(mutableListOf(), ::mapToDomain)
}
