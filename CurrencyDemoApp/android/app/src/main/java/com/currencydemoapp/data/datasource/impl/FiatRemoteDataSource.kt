package com.currencydemoapp.data.datasource.impl

import TYPE_FIAT
import com.currencydemoapp.data.datasource.CurrencyRemoteDataSource
import com.currencydemoapp.data.model.CurrencyInfo

class FiatRemoteDataSource : CurrencyRemoteDataSource {
    override suspend fun get(): List<CurrencyInfo> {
        return listOf(
            CurrencyInfo(
                id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD", type = TYPE_FIAT
            ),
            CurrencyInfo(id = "EUR", name = "Euro", symbol = "€", code = "EUR", type = TYPE_FIAT),
            CurrencyInfo(
                id = "GBP", name = "British Pound", symbol = "£", code = "GBP", type = TYPE_FIAT
            ),
            CurrencyInfo(
                id = "HKD", name = "Hong Kong Dollar", symbol = "$", code = "HKD", type = TYPE_FIAT
            ),
            CurrencyInfo(
                id = "JPY", name = "Japanese Yen", symbol = "¥", code = "JPY", type = TYPE_FIAT
            ),
            CurrencyInfo(
                id = "AUD", name = "Australian Dollar", symbol = "$", code = "AUD", type = TYPE_FIAT
            ),
            CurrencyInfo(
                id = "USD",
                name = "United States Dollar",
                symbol = "$",
                code = "USD",
                type = TYPE_FIAT
            )
        )
    }
}
