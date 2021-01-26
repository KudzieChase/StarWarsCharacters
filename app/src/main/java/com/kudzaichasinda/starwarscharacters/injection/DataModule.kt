package com.kudzaichasinda.starwarscharacters.injection

import com.kudzaichasinda.starwarscharacters.BuildConfig
import com.kudzaichasinda.starwarscharacters.data.remote.factory.RetrofitServiceFactory
import com.kudzaichasinda.starwarscharacters.data.remote.service.ApiService
import com.kudzaichasinda.starwarscharacters.data.repository.CharacterRepositoryImpl
import com.kudzaichasinda.starwarscharacters.data.repository.SearchRepositoryImpl
import com.kudzaichasinda.starwarscharacters.domain.repository.CharacterRepository
import com.kudzaichasinda.starwarscharacters.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    }

    @Binds
    @Singleton
    abstract fun bindsCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository
}