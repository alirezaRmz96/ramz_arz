package com.example.ramzarz.domain.repository

import com.example.ramzarz.data.model.Token
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.data.until.Resource
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun getToken():Resource<Token>
    suspend fun getTokens(): List<TokenItem>?
    suspend fun updateTokens():List<TokenItem>?
}