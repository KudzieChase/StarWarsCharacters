package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Character

interface SearchRepository {

    fun searchCharacter(name: String): List<Character>

}