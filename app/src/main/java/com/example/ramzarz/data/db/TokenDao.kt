package com.example.ramzarz.data.db

import androidx.room.*
import com.example.ramzarz.data.model.token.TokensItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(tokenItem: List<TokensItem>)

    @Query("DELETE FROM  tokens_table")
    suspend fun deleteAllToken()

    @Query("SELECT * FROM tokens_table")
    suspend fun getToken(): List<TokensItem>

    @Query("SELECT * FROM tokens_table WHERE favorite_token = 1")
    fun getFavoriteTokens():Flow<List<TokensItem>>

    @Update
    suspend fun updateTokens(tokenItem: TokensItem)

}