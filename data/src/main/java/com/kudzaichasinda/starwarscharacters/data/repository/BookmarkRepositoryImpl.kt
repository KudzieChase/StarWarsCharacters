package com.kudzaichasinda.starwarscharacters.data.repository

import com.kudzaichasinda.starwarscharacters.data.mapper.BookmarkEntityMapper
import com.kudzaichasinda.starwarscharacters.data.persistence.dao.BookmarkDao
import com.kudzaichasinda.starwarscharacters.domain.model.Bookmark
import com.kudzaichasinda.starwarscharacters.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao,
    val mapper: BookmarkEntityMapper
) : BookmarkRepository{

    override suspend fun saveBookmark(bookmark: Bookmark) {
        dao.insertBookmark(mapper.mapToEntity(bookmark))
    }
}