package com.beside.hackathon.core.di

import android.content.Context
import android.content.SharedPreferences
import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.repository.home.HomeRepository
import com.beside.hackathon.data.repository.record.CardNewsRecordRepository
import com.beside.hackathon.data.repository.record.SummariesRecordRepository
import com.beside.hackathon.data.repository.user.TokenRepository
import com.beside.hackathon.data.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ApiService
    ): UserRepository {
        return UserRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideTokenRepository(
        sharedPreferences: SharedPreferences
    ): TokenRepository {
        return TokenRepository(sharedPreferences)
    }

    @Singleton
    @Provides
    fun providerHomeRepository(
        apiService: ApiService
    ): HomeRepository {
        return HomeRepository(apiService)
    }


    @Singleton
    @Provides
    fun provideCardNewsRecordRepository(
        apiService: ApiService
    ): CardNewsRecordRepository {
        return CardNewsRecordRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSummariesRecordRepository(
        apiService: ApiService
    ): SummariesRecordRepository {
        return SummariesRecordRepository(apiService)
    }

}