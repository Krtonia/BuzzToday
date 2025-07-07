package com.app.buzztoday.di

import android.app.Application
import androidx.room.Room
import com.app.buzztoday.data.internal.Base
import com.app.buzztoday.data.internal.Convertor
import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.data.manager.LocalUserManagerImpl
import com.app.buzztoday.data.remote.NewsApi
import com.app.buzztoday.data.repository.NewsRepoImpl
import com.app.buzztoday.domain.manager.LocalUserManager
import com.app.buzztoday.domain.repository.NewsRepo
import com.app.buzztoday.domain.usecases.AppEntry
import com.app.buzztoday.domain.usecases.Read
import com.app.buzztoday.domain.usecases.Save
import com.app.buzztoday.domain.usecases.news.News
import com.app.buzztoday.domain.usecases.news.NewsCases
import com.app.buzztoday.domain.usecases.news.Search
import com.app.buzztoday.util.Constants.BASE_URL
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

    @Provides
    @Singleton
    fun NewsDb(
        application: Application
    ): Base {
        return Room.databaseBuilder(
            context = application,
            klass = Base::class.java,
            name = "news_db"
        ).addTypeConverter(Convertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun PbNewsDao(
        newDatabase: Base
    ): Dao = newDatabase.Dao

}

