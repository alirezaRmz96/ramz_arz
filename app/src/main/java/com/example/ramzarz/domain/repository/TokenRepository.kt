package com.example.ramzarz.domain.repository

import com.example.ramzarz.data.model.token.Tokens
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.until.Resource
import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    suspend fun getTokens(): List<TokensItem>?
    suspend fun updateTokens():List<TokensItem>?
    fun getFavoriteToken():Flow<List<TokensItem>>
    suspend fun update(tokenItem: TokensItem)
}