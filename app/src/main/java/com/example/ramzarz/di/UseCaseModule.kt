package com.example.ramzarz.di

import com.example.ramzarz.domain.repository.TokenRepository
import com.example.ramzarz.domain.useCase.GetTokenUseCase
import com.example.ramzarz.domain.useCase.GetTokensUseCase
import com.example.ramzarz.domain.useCase.TokenUseCase
import com.example.ramzarz.domain.useCase.UpdateTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideTokenUseCase(repository: TokenRepository):TokenUseCase{
        return TokenUseCase(
            getTokenUseCase = GetTokenUseCase(repository),
            getTokensUseCase = GetTokensUseCase(repository),
            updateTokenUseCase = UpdateTokenUseCase(repository)
        )
    }
}