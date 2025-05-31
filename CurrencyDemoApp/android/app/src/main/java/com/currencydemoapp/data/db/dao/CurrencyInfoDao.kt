package com.currencydemoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currencydemoapp.data.db.entity.CurrencyInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM currency_info")
    fun getAllCurrencies(): Flow<List<CurrencyInfoEntity>>

    @Query("SELECT * FROM currency_info WHERE type = :type")
    fun getCurrenciesByType(type: Int): Flow<List<CurrencyInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyInfoEntity>)

    @Query("DELETE FROM currency_info")
    suspend fun deleteAllCurrencies()

    @Query("DELETE FROM currency_info WHERE type = :type")
    suspend fun deleteCurrenciesByType(type: Int)
}
