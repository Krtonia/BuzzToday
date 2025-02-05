package com.example.buzztoday.di

import android.app.Application
import com.example.buzztoday.data.manager.LocalUserManagerImpl
import com.example.buzztoday.domain.manager.LocalUserManager
import com.example.buzztoday.domain.usecases.AppEntryUseCases
import com.example.buzztoday.domain.usecases.ReadAppEntry
import com.example.buzztoday.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}