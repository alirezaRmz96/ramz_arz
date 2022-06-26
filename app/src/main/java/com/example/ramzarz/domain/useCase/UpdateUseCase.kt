package com.example.ramzarz.domain.useCase

import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.domain.repository.TokenRepository

class UpdateUseCase(
    private val repository: TokenRepository
) {
    suspend fun execute(tokenItem: TokenItem){
        repository.update(tokenItem)
    }
}