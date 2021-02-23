package com.kudzaichasinda.starwarscharacters.domain.repository

import com.kudzaichasinda.starwarscharacters.domain.model.Bookmark

interface BookmarkRepository {

    suspend fun saveBookmark(bookmark: Bookmark)
}