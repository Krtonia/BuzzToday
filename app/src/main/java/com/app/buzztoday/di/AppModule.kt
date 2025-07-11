package com.app.buzztoday.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.buzztoday.data.internal.Base
import com.app.buzztoday.data.internal.Dao
import com.app.buzztoday.data.manager.LocalUserManagerImpl
import com.app.buzztoday.data.remote.NewsApi
import com.app.buzztoday.data.repository.NewsRepoImpl
import com.app.buzztoday.domain.manager.LocalUserManager
import com.app.buzztoday.domain.repository.NewsRepo
import com.app.buzztoday.domain.usecases.AppEntry
import com.app.buzztoday.domain.usecases.Read
import com.app.buzztoday.domain.usecases.Save
import com.app.buzztoday.domain.usecases.news.Delete
import com.app.buzztoday.domain.usecases.news.Get
import com.app.buzztoday.domain.usecases.news.GetArticle
import com.app.buzztoday.domain.usecases.news.News
import com.app.buzztoday.domain.usecases.news.NewsCases
import com.app.buzztoday.domain.usecases.news.Search
import com.app.buzztoday.domain.usecases.news.Upsert
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
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: Dao): NewsRepo {
        return NewsRepoImpl(newsApi,newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepo: NewsRepo,newsDao: Dao): NewsCases {
        return NewsCases(
            news = News(newsRepo),
            search = Search(newsRepo),
            searchNews = Search(newsRepo),
            upsertArticle = Upsert(newsDao),
            deleteArticle = Delete(newsDao),
            getArticles = Get(newsDao),
            getArticle = GetArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun NewsDb(
        application: Application
    ): RoomDatabase.Builder<Base> {
        return Room.databaseBuilder(
            context = application,
            klass = Base::class.java,
            name = "news_db"
        )
    }

    @Provides
    @Singleton
    fun PbNewsDao(
        newDatabase: Base
    ): Dao = newDatabase.dao

}