package com.example.ramzarz.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ramzarz.data.model.TokenItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(tokenItem: List<TokenItem>)

    @Query("DELETE FROM  token_table")
    suspend fun deleteAllToken()

    @Query("SELECT * FROM token_table")
    suspend fun getToken(): List<TokenItem>

    @Query("SELECT * FROM token_table WHERE favorite_token = 1")
    fun getFavoriteTokens():Flow<List<TokenItem>>

}