package com.example.ramzarz.di

import com.example.ramzarz.data.repository.TokenRepositoryImpl
import com.example.ramzarz.data.repository.dataSource.TokenCacheDataSource
import com.example.ramzarz.data.repository.dataSource.TokenLocalDataSource
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import com.example.ramzarz.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideTokenRepository(
        remoteDataSource: TokenRemoteDataSource,
        localDataSource: TokenLocalDataSource,
        cacheDataSource: TokenCacheDataSource
    ):TokenRepository{
        return TokenRepositoryImpl(remoteDataSource,localDataSource,cacheDataSource)
    }
}