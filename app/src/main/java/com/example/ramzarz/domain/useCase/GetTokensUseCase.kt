package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.domain.repository.TokenRepository

class GetTokensUseCase(
    private val tokenRepository: TokenRepository
) {
    suspend fun execute():List<TokenItem>? = tokenRepository.getTokens()
}