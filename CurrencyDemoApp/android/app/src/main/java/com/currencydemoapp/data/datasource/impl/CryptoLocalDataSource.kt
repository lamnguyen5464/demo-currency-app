package com.currencydemoapp.data.datasource.impl

import TYPE_CRYPTO
import com.currencydemoapp.data.datasource.CurrencyLocalDataSource
import com.currencydemoapp.data.db.dao.CurrencyInfoDao
import com.currencydemoapp.data.mapper.toEntity
import com.currencydemoapp.data.mapper.toModel
import com.currencydemoapp.data.model.CurrencyInfo
import kotlinx.coroutines.flow.first

class CryptoLocalDataSource(
    private val currencyInfoDao: CurrencyInfoDao
) : CurrencyLocalDataSource {
    override suspend fun get(): List<CurrencyInfo> {
        return currencyInfoDao.getCurrenciesByType(TYPE_CRYPTO).first().map { it.toModel() }
    }

    override suspend fun set(data: List<CurrencyInfo>) {
        currencyInfoDao.insertCurrencies(data.map { it.toEntity() })
    }

    override suspend fun clear() {
        currencyInfoDao.deleteCurrenciesByType(TYPE_CRYPTO)
    }
}