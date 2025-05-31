package com.currencydemoapp.data.datasource

import com.currencydemoapp.data.model.CurrencyInfo

interface CurrencyLocalDataSource {
    suspend fun get(): List<CurrencyInfo>
    suspend fun set(data: List<CurrencyInfo>)
    suspend fun clear()
}
