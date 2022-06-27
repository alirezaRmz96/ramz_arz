package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.until.Resource
import com.example.ramzarz.domain.repository.TokenRepository

class GetTokensUseCase(
    private val tokenRepository: TokenRepository
) {
    suspend fun execute():List<TokensItem>? = tokenRepository.getTokens()
}