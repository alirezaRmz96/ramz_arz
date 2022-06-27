package com.example.ramzarz.domain.useCase

data class TokenUseCase (
    val getTokensUseCase: GetTokensUseCase,
    val updateTokenUseCase: UpdateTokenUseCase,
    val getFavoriteTokenUseCase: GetFavoriteTokenUseCase,
    val updateUseCase: UpdateUseCase
)