package com.example.buzztoday.di

import android.app.Application
import com.example.buzztoday.data.manager.LocalUserManagerImpl
import com.example.buzztoday.data.remote.NewsApi
import com.example.buzztoday.data.repository.NewsRepoImpl
import com.example.buzztoday.domain.manager.LocalUserManager
import com.example.buzztoday.domain.repository.NewsRepo
import com.example.buzztoday.domain.usecases.AppEntry
import com.example.buzztoday.domain.usecases.Read
import com.example.buzztoday.domain.usecases.Save
import com.example.buzztoday.domain.usecases.news.News
import com.example.buzztoday.domain.usecases.news.NewsCases
import com.example.buzztoday.domain.usecases.news.Search
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
        AppEntry(
            read = Read(localUserManager),
            save = Save(localUserManager)
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
    fun provideNewsRepository(newsApi: NewsApi): NewsRepo = NewsRepoImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepo: NewsRepo): NewsCases {
        return NewsCases(news = News(newsRepo), search = Search(newsRepo))
    }

}

