package com.currencydemoapp.domain.usecase

import com.currencydemoapp.data.model.CurrencyInfo

interface CurrencyUseCase {
    suspend fun clearAll()
    suspend fun refresh()
    suspend fun getCryptoList(): List<CurrencyInfo>
    suspend fun getFiatList(): List<CurrencyInfo>
    suspend fun getAll(): List<CurrencyInfo>
}
