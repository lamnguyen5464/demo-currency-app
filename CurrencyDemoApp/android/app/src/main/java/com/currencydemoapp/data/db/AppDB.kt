package com.currencydemoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.currencydemoapp.data.db.dao.CurrencyInfoDao
import com.currencydemoapp.data.db.entity.CurrencyInfoEntity

@Database(entities = [CurrencyInfoEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun currencyInfoDao(): CurrencyInfoDao
}