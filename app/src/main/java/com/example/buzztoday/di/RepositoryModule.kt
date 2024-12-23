package com.example.buzztoday.di

import com.example.buzztoday.data.web.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun prov(api: NewsApi): com.example.buzztoday.domain.repository.NewsRepository {
        return com.example.buzztoday.data.repository.NewsRepositoryImpl(api)
    }
}