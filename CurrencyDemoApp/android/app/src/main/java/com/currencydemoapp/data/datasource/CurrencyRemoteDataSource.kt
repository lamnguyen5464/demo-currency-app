package com.currencydemoapp.data.datasource

import com.currencydemoapp.data.model.CurrencyInfo

interface CurrencyRemoteDataSource {
    suspend fun get(): List<CurrencyInfo>
}
