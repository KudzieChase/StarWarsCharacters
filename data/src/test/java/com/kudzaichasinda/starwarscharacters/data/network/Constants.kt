package com.kudzaichasinda.starwarscharacters.data.network

object Constants {
    const val SEARCH_QUERY = "Lu"
    const val SEARCH_QUERY_FAIL = "Grogu"
    const val SEARCH_QUERY_URL = "/people/?search=$SEARCH_QUERY"
    const val SEARCH_QUERY_FAIL_URL = "/people/?search=$SEARCH_QUERY_FAIL"
    const val FILMS_URL = "/films/1/"
    const val CHARACTER_URL = "/people/1/"
    const val PLANETS_URL = "/planets/1/"
    const val SPECIE_URL = "/species/1/"
    const val URL_NOT_FOUND = "/people/2395/"
}