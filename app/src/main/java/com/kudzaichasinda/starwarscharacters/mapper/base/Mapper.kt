package com.kudzaichasinda.starwarscharacters.mapper.base

interface Mapper<out V, in D> {
    fun mapToView(domain: D): V

    fun mapToViewList(list: List<D>): List<V> = list.mapTo(mutableListOf(), ::mapToView)
}
