package com.example.ramzarz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ramzarz.data.model.TokenItem

@Database(entities = [TokenItem::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TokenRoomDataBase : RoomDatabase() {

    abstract fun tokenDao():TokenDao
}