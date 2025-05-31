package com.currencydemoapp.domain.repository

import com.currencydemoapp.data.datasource.CurrencyLocalDataSource
import com.currencydemoapp.data.datasource.CurrencyRemoteDataSource
import com.currencydemoapp.data.model.CurrencyInfo
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface CurrencyRepository {
    suspend fun fetch(): List<CurrencyInfo>
    suspend fun get(): List<CurrencyInfo>
    suspend fun clear()
}

