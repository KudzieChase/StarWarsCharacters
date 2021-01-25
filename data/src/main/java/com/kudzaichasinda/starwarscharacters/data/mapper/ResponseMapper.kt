package com.kudzaichasinda.starwarscharacters.data.mapper

interface ResponseMapper<in E, out D> {
    fun mapToDomain(entity: E): D
}