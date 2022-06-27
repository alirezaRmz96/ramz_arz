package com.example.ramzarz.data.repository

import android.util.Log
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.repository.dataSource.TokenLocalDataSource
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import com.example.ramzarz.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow


class TokenRepositoryImpl (
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
) : TokenRepository{

    override suspend fun getTokens(): List<TokensItem>? {
        return getTokenFromDB()
    }

    // for update the whole of database when we want
    override suspend fun updateTokens(): List<TokensItem>? {
        val newTokens = getTokenFromApi()
        tokenLocalDataSource.clearAll()
        tokenLocalDataSource.saveTokenToDB(newTokens)
        return newTokens
    }

    override fun getFavoriteToken(): Flow<List<TokensItem>> {
        return tokenLocalDataSource.getFavoriteToken()
    }

    override suspend fun update(tokenItem: TokensItem) {

        tokenLocalDataSource.updateTokens(tokenItem)
        val newTokens = getTokenFromDB()
        tokenLocalDataSource.saveTokenToDB(newTokens)


    }



    private suspend fun getTokenFromDB():List<TokensItem>{
        lateinit var tokens : List<TokensItem>
        try {
            tokens = tokenLocalDataSource.getTokenFromDB()
        }catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (tokens.isNotEmpty()){
            return tokens
        }
        else {
            tokens = getTokenFromApi()
            tokenLocalDataSource.saveTokenToDB(tokens)
        }
        return tokens
    }

    private suspend fun getTokenFromApi():List<TokensItem>{
        lateinit var tokens : List<TokensItem>
        try {
            val response = tokenRemoteDataSource.getToken()
            val body  = response.body()
            if (body!=null){
                tokens = body
            }
        }
        catch (exception: java.lang.Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return tokens
    }

}