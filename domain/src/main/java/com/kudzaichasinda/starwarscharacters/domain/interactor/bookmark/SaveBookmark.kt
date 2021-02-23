package com.kudzaichasinda.starwarscharacters.domain.interactor.bookmark

import com.kudzaichasinda.starwarscharacters.domain.interactor.base.UseCase
import com.kudzaichasinda.starwarscharacters.domain.model.Bookmark
import com.kudzaichasinda.starwarscharacters.domain.repository.BookmarkRepository
import javax.inject.Inject

class SaveBookmark @Inject constructor(
    private val repository: BookmarkRepository
) : UseCase<Unit,Bookmark>() {
    override suspend fun invoke(params: Bookmark) {
        return repository.saveBookmark(params)
    }
}