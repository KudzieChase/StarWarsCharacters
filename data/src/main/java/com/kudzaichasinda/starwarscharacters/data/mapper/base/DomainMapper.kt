package com.kudzaichasinda.starwarscharacters.data.mapper.base

interface DomainMapper<in E, out D> {
    fun mapToDomain(entity: E): D

    fun mapToDomainList(list: List<E>): List<D> = list.mapTo(mutableListOf(), ::mapToDomain)
}