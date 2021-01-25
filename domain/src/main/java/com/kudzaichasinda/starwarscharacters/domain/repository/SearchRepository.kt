package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCharacter(name: String): Flow<List<Character>>
}