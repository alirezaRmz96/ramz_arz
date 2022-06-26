package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): Flow<List<TokenItem>> {
        return tokenRepository.getFavoriteToken()
    }
}