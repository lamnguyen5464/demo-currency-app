package com.currencydemoapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info")
open class CurrencyInfoEntity(
    val type: Int,
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val code: String
)
