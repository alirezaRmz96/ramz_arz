package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): Flow<List<TokensItem>> {
        return tokenRepository.getFavoriteToken()
    }
}