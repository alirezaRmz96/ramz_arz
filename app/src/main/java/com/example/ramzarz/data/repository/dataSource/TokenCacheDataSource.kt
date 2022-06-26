package com.example.ramzarz.data.repository.dataSource

import com.example.ramzarz.data.model.TokenItem
import kotlinx.coroutines.flow.Flow

interface TokenCacheDataSource {
    suspend fun getTokenFromCache(): List<TokenItem>
    suspend fun saveTokenToCache(tokens:List<TokenItem>)
}