package com.kudzaichasinda.starwarscharacters.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmEntity(
    @Json(name = "title") val title: String,
    @Json(name = "opening_crawl") val openingCrawl: String
)
