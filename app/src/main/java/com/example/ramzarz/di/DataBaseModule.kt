package com.example.ramzarz.di

import android.app.Application
import androidx.room.Room
import com.example.ramzarz.data.db.TokenDao
import com.example.ramzarz.data.db.TokenRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideTokenDataBase(app:Application):TokenRoomDataBase{
        return Room.databaseBuilder(
            app,
            TokenRoomDataBase::class.java,
            "token_table"
        )
        .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTokenDao(tokenRoomDataBase: TokenRoomDataBase):TokenDao{
        return tokenRoomDataBase.tokenDao()
    }
}