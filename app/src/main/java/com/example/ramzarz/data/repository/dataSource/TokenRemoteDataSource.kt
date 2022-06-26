package com.example.ramzarz.data.repository.dataSource

import com.example.ramzarz.data.model.Token
import retrofit2.Response

interface TokenRemoteDataSource {
    suspend fun getToken():Response<Token>
}