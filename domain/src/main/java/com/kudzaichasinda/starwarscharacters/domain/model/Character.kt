package com.kudzaichasinda.starwarscharacters.domain.model

data class Character(
    val name: String,
    val height: String,
    val birthYear: String,
    val films: List<String>,
    val species: List<String>,
    val homeWorld: String,
    val url: String
)
