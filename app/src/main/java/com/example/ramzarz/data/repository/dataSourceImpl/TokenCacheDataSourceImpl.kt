package com.example.ramzarz.data.repository.dataSourceImpl

import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.data.repository.dataSource.TokenCacheDataSource

class TokenCacheDataSourceImpl : TokenCacheDataSource{
    private var tokensList = ArrayList<TokenItem>()
    override suspend fun getTokenFromCache(): List<TokenItem> {
        return tokensList
    }

    override suspend fun saveTokenToCache(tokens: List<TokenItem>) {
        tokensList.clear()
        tokensList = ArrayList(tokens)
    }
}