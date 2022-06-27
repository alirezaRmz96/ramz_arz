package com.example.ramzarz.data.repository.dataSourceImpl

import com.example.ramzarz.data.api.TokenApi
import com.example.ramzarz.data.model.token.Tokens
import com.example.ramzarz.data.repository.dataSource.TokenRemoteDataSource
import retrofit2.Response

class TokenRemoteDataSourceImpl(
    private val tokenApi: TokenApi
) : TokenRemoteDataSource{
    override suspend fun getToken(): Response<Tokens> {
        return tokenApi.getToken()
    }
}