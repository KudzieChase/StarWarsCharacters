package com.kudzaichasinda.starwarscharacters.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetEntity(
    @Json(name = "name") val name: String,
    @Json(name = "population") val population: String
)
