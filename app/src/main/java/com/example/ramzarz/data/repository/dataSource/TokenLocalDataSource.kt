package com.example.ramzarz.data.repository.dataSource

import com.example.ramzarz.data.model.TokenItem
import kotlinx.coroutines.flow.Flow


interface TokenLocalDataSource {
    suspend fun getTokenFromDB(): List<TokenItem>
    suspend fun saveTokenToDB(token: List<TokenItem>)
    suspend fun clearAll()
    fun getFavoriteToken():Flow<List<TokenItem>>
    suspend fun updateTokens(tokenItem: TokenItem)
}