package com.example.ramzarz.data.repository.dataSourceImpl

import com.example.ramzarz.data.api.TokenApi
import com.example.ramzarz.data.model.Token
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import retrofit2.Response

class TokenRemoteDataSourceImpl(
    private val tokenApi: TokenApi
) : TokenRemoteDataSource{
    override suspend fun getToken(): Response<Token> {
        return tokenApi.getToken()
    }
}