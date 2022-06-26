package com.example.ramzarz.data.repository

import android.text.TextUtils.replace
import android.util.Log
import com.example.ramzarz.data.model.Token
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.data.repository.dataSource.TokenCacheDataSource
import com.example.ramzarz.data.repository.dataSource.TokenLocalDataSource
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import com.example.ramzarz.data.repository.dataSourceImpl.TokenRemoteDataSourceImpl
import com.example.ramzarz.data.until.Resource
import com.example.ramzarz.domain.repository.TokenRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.util.*

class TokenRepositoryImpl (
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val tokenCacheDataSource: TokenCacheDataSource
) : TokenRepository{
    private var tool :Boolean = true

    override suspend fun getToken(): Resource<Token> {
        return responseToResource(
            tokenRemoteDataSource.getToken()
        )
    }

    override suspend fun getTokens(): List<TokenItem>? {
        return getTokensFromCache()
    }

    override suspend fun updateTokens(): List<TokenItem>? {
        val newTokens = getTokenFromApi()
        tokenLocalDataSource.clearAll()
        tokenLocalDataSource.saveTokenToDB(newTokens)
        tokenCacheDataSource.saveTokenToCache(newTokens)
        return newTokens
    }

    override fun getFavoriteToken(): Flow<List<TokenItem>> {
        return tokenLocalDataSource.getFavoriteToken()
    }

    override suspend fun update(tokenItem: TokenItem) {

        tokenLocalDataSource.updateTokens(tokenItem)
        val newTokens = getTokenFromDB()

        tokenLocalDataSource.saveTokenToDB(newTokens)

        tool = false

    }

    suspend fun getTokensFromCache(): List<TokenItem> {
        lateinit var tokens : List<TokenItem>
        try {
            tokens = tokenCacheDataSource.getTokenFromCache()
        }catch (exception:Exception){
            Log.i("MyTag", exception.message.toString())
        }
        if (tokens.size>0 &&  tool){
            return tokens
        }
        else {
            tokens = getTokenFromDB()
            tokenCacheDataSource.saveTokenToCache(tokens)
        }
        return tokens
    }

    suspend fun getTokenFromDB():List<TokenItem>{
        lateinit var tokens : List<TokenItem>
        try {
            tokens = tokenLocalDataSource.getTokenFromDB()
        }catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (tokens.size>0){
            return tokens
        }
        else {
            tokens = getTokenFromApi()
            tokenLocalDataSource.saveTokenToDB(tokens)
        }
        return tokens
    }

    suspend fun getTokenFromApi():List<TokenItem>{
        lateinit var tokens : List<TokenItem>
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

    private fun responseToResource(response : Response<Token>):Resource<Token>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}