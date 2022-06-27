package com.example.ramzarz.di

import com.example.ramzarz.data.api.TokenApi
import com.example.ramzarz.data.db.TokenDao
import com.example.ramzarz.data.repository.TokenRepositoryImpl
import com.example.ramzarz.data.repository.dataSource.TokenLocalDataSource
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import com.example.ramzarz.data.repository.dataSourceImpl.TokenLocalDataSourceImpl
import com.example.ramzarz.data.repository.dataSourceImpl.TokenRemoteDataSourceImpl
import com.example.ramzarz.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Singleton
    @Provides
    fun provideTokenRemote(api: TokenApi): TokenRemoteDataSource {
        return TokenRemoteDataSourceImpl(api)
    }
    @Singleton
    @Provides
    fun provideTokenLocal(tokenDao: TokenDao):TokenLocalDataSource{
        return TokenLocalDataSourceImpl(tokenDao)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    class RepositoryModule {
        @Singleton
        @Provides
        fun provideTokenRepository(
            remoteDataSource: TokenRemoteDataSource,
            localDataSource: TokenLocalDataSource,
        ): TokenRepository {
            return TokenRepositoryImpl(remoteDataSource,localDataSource)
        }
    }
}