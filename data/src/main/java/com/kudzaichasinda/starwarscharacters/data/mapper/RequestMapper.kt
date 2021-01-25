package com.kudzaichasinda.starwarscharacters.data.mapper

interface RequestMapper<in D, out E> {
    fun mapToEntity(domain: D): E
}