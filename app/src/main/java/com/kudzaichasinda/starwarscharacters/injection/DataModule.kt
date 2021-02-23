package com.kudzaichasinda.starwarscharacters.injection

import android.content.Context
import androidx.room.Room
import com.kudzaichasinda.starwarscharacters.BuildConfig
import com.kudzaichasinda.starwarscharacters.data.persistence.dao.BookmarkDao
import com.kudzaichasinda.starwarscharacters.data.persistence.db.StarWarsDatabase
import com.kudzaichasinda.starwarscharacters.data.remote.factory.RetrofitServiceFactory
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.data.repository.BookmarkRepositoryImpl
import com.kudzaichasinda.starwarscharacters.data.repository.CharacterRepositoryImpl
import com.kudzaichasinda.starwarscharacters.data.repository.SearchRepositoryImpl
import com.kudzaichasinda.starwarscharacters.domain.repository.BookmarkRepository
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    companion object {
        @Provides
        @Singleton
        fun providesApiService(): ApiService {
            return RetrofitServiceFactory.makeApiService(BuildConfig.DEBUG)
        }

        @Provides
        @Singleton
        fun providesStarWarsDatabase(@ApplicationContext context: Context): StarWarsDatabase {
            return Room.databaseBuilder(context, StarWarsDatabase::class.java, "starwarsdb").build()
        }

        @Provides
        @Singleton
        fun providesBookmarkDao(database: StarWarsDatabase): BookmarkDao {
            return database.bookmarkDao()
        }
    }

    @Binds
    @Singleton
    abstract fun bindsCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindsBookmarkRepository(bookmarkRepository: BookmarkRepositoryImpl): BookmarkRepository
}