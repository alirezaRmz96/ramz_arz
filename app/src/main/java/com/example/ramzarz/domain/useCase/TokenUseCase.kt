package com.example.ramzarz.domain.useCase

data class TokenUseCase (
    val getTokenUseCase: GetTokenUseCase,
    val getTokensUseCase: GetTokensUseCase,
    val updateTokenUseCase: UpdateTokenUseCase
)