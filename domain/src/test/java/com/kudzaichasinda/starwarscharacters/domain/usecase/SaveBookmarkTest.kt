package com.kudzaichasinda.starwarscharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.domain.data.FakeData
import com.kudzaichasinda.starwarscharacters.domain.interactor.bookmark.SaveBookmark
import com.kudzaichasinda.starwarscharacters.domain.model.Bookmark
import com.kudzaichasinda.starwarscharacters.domain.repository.BookmarkRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SaveBookmarkTest {

    @Mock
    private lateinit var repository: BookmarkRepository

    private lateinit var saveBookmark: SaveBookmark

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        saveBookmark = SaveBookmark(repository)
    }

    private suspend fun stubSaveBookmark(bookmark: Bookmark) {
        whenever(saveBookmark(bookmark)).thenReturn(Unit)
    }

    @Test
    fun `checks SaveBookmark() saves a bookmark`() = runBlockingTest {

        val bookmark = Bookmark(FakeData.fakeCharacter.name)

        stubSaveBookmark(bookmark)

        val useCase = saveBookmark(bookmark)

        assertThat(useCase).isInstanceOf(Unit::class.java)
    }
}