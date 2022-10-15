package com.kudzaichasinda.starwarscharacters.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kudzaichasinda.starwarscharacters.mapper.CharacterViewMapper
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
    }
}
