package com.example.ramzarz.data.repository.dataSourceImpl

import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.repository.dataSource.TokenCacheDataSource

class TokenCacheDataSourceImpl : TokenCacheDataSource{
    private var tokensList = ArrayList<TokensItem>()
    override suspend fun getTokenFromCache(): List<TokensItem> {
        return tokensList
    }

    override suspend fun saveTokenToCache(tokens: List<TokensItem>) {
        tokensList.clear()
        tokensList = ArrayList(tokens)
    }
}