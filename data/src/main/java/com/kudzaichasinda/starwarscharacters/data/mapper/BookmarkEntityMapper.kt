package com.kudzaichasinda.starwarscharacters.data.mapper

import com.kudzaichasinda.starwarscharacters.data.mapper.base.EntityMapper
import com.kudzaichasinda.starwarscharacters.data.persistence.entity.BookmarkEntity
import com.kudzaichasinda.starwarscharacters.domain.model.Bookmark
import javax.inject.Inject

class BookmarkEntityMapper @Inject constructor() : EntityMapper<Bookmark, BookmarkEntity> {
    override fun mapToEntity(domain: Bookmark): BookmarkEntity {
        return BookmarkEntity(name = domain.name)
    }

}