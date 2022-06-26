package com.example.ramzarz.data.db

import androidx.room.TypeConverter
import com.example.ramzarz.data.model.*

class Converter {
    @TypeConverter
    fun fromYtd(ytd: Ytd): String?{
        return ytd.price_change_pct
    }
    @TypeConverter
    fun toYtd(price_change_pct : String):Ytd{
        return Ytd(price_change_pct, price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct)
    }
    @TypeConverter
    fun fromD(d: D): String?{
        return d!!.price_change_pct
    }
    @TypeConverter
    fun toD(price_change_pct: String?): D {
        return D(price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct)
    }
    @TypeConverter
    fun fromDX(dx: DX): String?{
        return dx.price_change_pct
    }
    @TypeConverter
    fun toDX(price_change_pct: String):DX{
        return DX(price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct)
    }
    @TypeConverter
    fun fromDXX(dxx: DXX): String?{
        return dxx.price_change_pct
    }
    @TypeConverter
    fun toDXX(price_change_pct: String):DXX{
        return DXX(price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct)
    }
    @TypeConverter
    fun fromDXXX(dxxx: DXXX): String?{
        return dxxx.price_change_pct
    }
    @TypeConverter
    fun toDXXX(price_change_pct: String):DXXX{
        return DXXX(price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct,price_change_pct)
    }
}