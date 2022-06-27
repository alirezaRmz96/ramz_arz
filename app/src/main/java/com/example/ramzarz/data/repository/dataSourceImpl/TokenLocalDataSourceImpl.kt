package com.example.ramzarz.data.repository.dataSourceImpl

import com.example.ramzarz.data.db.TokenDao
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.repository.dataSource.TokenLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TokenLocalDataSourceImpl(
    private val tokenDao: TokenDao
):TokenLocalDataSource {
    override suspend fun getTokenFromDB(): List<TokensItem> {
        return tokenDao.getToken()
    }


    override suspend fun saveTokenToDB(token: List<TokensItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            tokenDao.insertToken(token)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tokenDao.deleteAllToken()
        }
    }

    override fun getFavoriteToken(): Flow<List<TokensItem>> {
        return tokenDao.getFavoriteTokens()
    }

    override suspend fun updateTokens(tokenItem: TokensItem) {
        return tokenDao.updateTokens(tokenItem)
    }
}