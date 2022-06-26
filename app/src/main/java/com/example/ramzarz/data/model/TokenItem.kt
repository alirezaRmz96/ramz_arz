package com.example.ramzarz.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "token_table")
data class TokenItem(
    @SerializedName("1d")
    val oneD: D? = null,
    @SerializedName("30d")
    val monthD: DX?,
    @SerializedName("365d")
    val yearD: DXX?,
    @SerializedName("7d")
    val weekD: DXXX?,

    val circulating_supply: String?,
    val currency: String?,
    val first_candle: String?,
    val first_order_book: String?,
    val first_priced_at: String?,
    val first_trade: String?,
    val high: String?,
    val high_timestamp: String?,
    @PrimaryKey(autoGenerate = false) val id: String ,
    val logo_url: String?,
    val market_cap: String?,
    val market_cap_dominance: String?,
    val max_supply: String?,
    val name: String?,
    val num_exchanges: String?,
    val num_pairs: String?,
    val num_pairs_unmapped: String?,
    val platform_currency: String?,
    val price: String?,
    val price_date: String?,
    val price_timestamp: String?,
    val rank: String?,
    val rank_delta: String?,
    val status: String?,
    val symbol: String?,
    val ytd: Ytd?,
    @ColumnInfo(name = "favorite_token") var favoriteToken:Boolean = false
):Serializable