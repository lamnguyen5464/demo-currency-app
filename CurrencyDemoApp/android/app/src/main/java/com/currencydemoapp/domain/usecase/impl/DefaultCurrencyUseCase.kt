package com.currencydemoapp.domain.usecase.impl

import com.currencydemoapp.data.model.CurrencyInfo
import com.currencydemoapp.domain.repository.CurrencyRepository
import com.currencydemoapp.domain.usecase.CurrencyUseCase


class DefaultCurrencyUseCase(
    private val cryptoRepository: CurrencyRepository,
    private val fiatRepository: CurrencyRepository
) : CurrencyUseCase {

    override suspend fun clearAll() {
        cryptoRepository.clear()
        fiatRepository.clear()
    }

    override suspend fun refresh() {
        cryptoRepository.fetch()
        fiatRepository.fetch()
        
    }

    override suspend fun getCryptoList(): List<CurrencyInfo> {
        return cryptoRepository.get()
    }

    override suspend fun getFiatList(): List<CurrencyInfo> {
        return fiatRepository.get()
    }

    override suspend fun getAll(): List<CurrencyInfo> {
        return cryptoRepository.get() + fiatRepository.get()
    }
}
