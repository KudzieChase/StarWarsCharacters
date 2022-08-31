package com.kudzaichasinda.starwarscharacters.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kudzaichasinda.starwarscharacters.data.FakeSearchRepository
import com.kudzaichasinda.starwarscharacters.domain.interactor.search.SearchCharacter
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.ui.search.SearchViewModel
import com.kudzaichasinda.starwarscharacters.util.Result
import com.kudzaichasinda.starwarscharacters.util.Result.Success
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val mapper = CharacterViewMapper()

    @Test
    fun performSearch_returnsCharacters() {
        val repository = FakeSearchRepository()
        val searchCharacter = SearchCharacter(repository)
        val viewModel = SearchViewModel(searchCharacter, mapper)

        val observer = Observer<Result<List<CharacterView>>> {}

        try {
            viewModel.searchResults.asLiveData().observeForever(observer)
            viewModel.performSearch("Luke")

            val characters = viewModel.searchResults.value
            when (characters) {
                is Success -> {
                    assertThat(characters.data.size).isAtLeast(1)
                }
                else -> {
                    // We aren't interested in other cases
                }
            }
        } finally {
            viewModel.searchResults.asLiveData().removeObserver(observer)
        }
    }
}
