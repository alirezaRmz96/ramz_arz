package com.example.ramzarz.data.api

import com.example.ramzarz.BuildConfig
import com.example.ramzarz.data.model.token.Tokens
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TokenApi {
    @GET("ticker")
    suspend fun getToken(
        @Query("key")
        apiKey:String = BuildConfig.API_KEY
    ):Response<Tokens>
}
