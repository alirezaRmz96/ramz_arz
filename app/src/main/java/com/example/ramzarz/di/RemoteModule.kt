package com.example.ramzarz.di

import com.example.ramzarz.data.api.TokenApi
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import com.example.ramzarz.data.repository.dataSourceImpl.TokenRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
//    @Singleton
//    @Provides
//    fun provideTokenRemote(api: TokenApi): TokenRemoteDataSource{
//        return TokenRemoteDataSourceImpl(api)
//    }
}