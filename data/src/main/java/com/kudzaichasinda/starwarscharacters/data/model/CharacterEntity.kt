package com.kudzaichasinda.starwarscharacters.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterEntity(
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: String,
    @Json(name = "birth_year") val birthYear: String,
    @Json(name = "films") val films: List<String>,
    @Json(name = "species") val species: List<String>,
    @Json(name = "homeworld") val homeWorld: String,
    @Json(name = "url") val url: String
)
