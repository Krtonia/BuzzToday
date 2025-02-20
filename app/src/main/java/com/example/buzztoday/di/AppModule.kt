package com.example.buzztoday.di

import android.app.Application
import com.example.buzztoday.data.manager.LocalUserManagerImpl
import com.example.buzztoday.data.remote.NewsApi
import com.example.buzztoday.data.repository.NewsRepositoryImpl
import com.example.buzztoday.domain.manager.LocalUserManager
import com.example.buzztoday.domain.repository.NewsRepository
import com.example.buzztoday.domain.usecases.AppEntryUseCases
import com.example.buzztoday.domain.usecases.ReadAppEntry
import com.example.buzztoday.domain.usecases.SaveAppEntry
import com.example.buzztoday.domain.usecases.news.GetNews
import com.example.buzztoday.domain.usecases.news.NewsUseCases
import com.example.buzztoday.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun providesAppEntryUseCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun ProvideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(getNews = GetNews(newsRepository))
    }

}

