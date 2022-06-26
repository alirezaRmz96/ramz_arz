package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.Token
import com.example.ramzarz.data.until.Resource
import com.example.ramzarz.domain.repository.TokenRepository

class GetTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    suspend fun execute():Resource<Token>{
        return tokenRepository.getToken()
    }
}