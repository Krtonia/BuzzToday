package com.example.buzztoday.di

import android.content.Context
import com.example.buzztoday.NewsApp
import com.example.buzztoday.data.web.NewsApi
import com.example.buzztoday.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context): NewsApp {
        return context as NewsApp
    }

    @Provides
    @Singleton
    fun retro(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun cont(@ApplicationContext context: Context):Context{
        return context
    }

    @Provides
    @Singleton
    fun okhttp(): OkHttpClient{
        return OkHttpClient.Builder().readTimeout(30L,java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30L,java.util.concurrent.TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun ret(retrofit: Retrofit): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }
}