package com.example.ramzarz.data.repository.dataSource

import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.until.Resource

interface TokenCacheDataSource {
    suspend fun getTokenFromCache(): List<TokensItem>
    suspend fun saveTokenToCache(tokens:List<TokensItem>)
}