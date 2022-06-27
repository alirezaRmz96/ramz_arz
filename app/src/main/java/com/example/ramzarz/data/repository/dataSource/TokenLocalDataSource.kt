package com.example.ramzarz.data.repository.dataSource

import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.until.Resource
import kotlinx.coroutines.flow.Flow


interface TokenLocalDataSource {
    suspend fun getTokenFromDB(): List<TokensItem>
    suspend fun saveTokenToDB(token: List<TokensItem>)
    suspend fun clearAll()
    fun getFavoriteToken():Flow<List<TokensItem>>
    suspend fun updateTokens(tokenItem: TokensItem)
}