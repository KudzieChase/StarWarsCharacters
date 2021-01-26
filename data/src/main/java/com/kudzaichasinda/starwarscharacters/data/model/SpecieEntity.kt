package com.kudzaichasinda.starwarscharacters.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecieEntity(
    @Json(name = "name") val name: String,
    @Json(name = "language") val language: String,
    @Json(name = "homeworld") val homeWorld: String?
)