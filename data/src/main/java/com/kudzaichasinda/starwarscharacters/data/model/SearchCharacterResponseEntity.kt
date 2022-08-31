package com.kudzaichasinda.starwarscharacters.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchCharacterResponseEntity(
    @Json(name = "count") val count: Int,
    @Json(name = "results") val results: List<CharacterEntity>
)
