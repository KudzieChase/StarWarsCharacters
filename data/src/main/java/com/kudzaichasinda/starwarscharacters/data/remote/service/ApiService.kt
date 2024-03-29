package com.kudzaichasinda.starwarscharacters.data.remote.service

import com.kudzaichasinda.starwarscharacters.data.model.CharacterEntity
import com.kudzaichasinda.starwarscharacters.data.model.FilmEntity
import com.kudzaichasinda.starwarscharacters.data.model.PlanetEntity
import com.kudzaichasinda.starwarscharacters.data.model.SearchCharacterResponseEntity
import com.kudzaichasinda.starwarscharacters.data.model.SpecieEntity
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/")
    suspend fun searchCharacter(@Query("search") characterName: String): SearchCharacterResponseEntity

    @GET
    suspend fun getCharacterInfo(@Url url: String): CharacterEntity

    @GET
    suspend fun getSpecieInfo(@Url url: String): SpecieEntity

    @GET
    suspend fun getPlanetInfo(@Url url: String): PlanetEntity

    @GET
    suspend fun getFilmInfo(@Url url: String): FilmEntity
}
