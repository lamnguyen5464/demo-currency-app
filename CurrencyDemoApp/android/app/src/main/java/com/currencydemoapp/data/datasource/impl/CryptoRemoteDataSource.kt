package com.currencydemoapp.data.datasource.impl

import TYPE_CRYPTO
import com.currencydemoapp.data.datasource.CurrencyRemoteDataSource
import com.currencydemoapp.data.model.CurrencyInfo

class CryptoRemoteDataSource : CurrencyRemoteDataSource {
    override suspend fun get(): List<CurrencyInfo> {
        return listOf(
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC",
                code = "BTC"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "ETH",
                name = "Ethereum",
                symbol = "ETH",
                code = "ETH"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "XRP",
                name = "XRP",
                symbol = "XRP",
                code = "XRP"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "BCH",
                name = "Bitcoin Cash",
                symbol = "BCH",
                code = "BCH"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "LTC",
                name = "Litecoin",
                symbol = "LTC",
                code = "LTC"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "EOS",
                name = "EOS",
                symbol = "EOS",
                code = "EOS"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "BNB",
                name = "Binance Coin",
                symbol = "BNB",
                code = "BNB"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "LINK",
                name = "Chainlink",
                symbol = "LINK",
                code = "LINK"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "NEO",
                name = "NEO",
                symbol = "NEO",
                code = "NEO"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "ETC",
                name = "Ethereum Classic",
                symbol = "ETC",
                code = "ETC"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "ONT",
                name = "Ontology",
                symbol = "ONT",
                code = "ONT"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "CRO",
                name = "Crypto.com Chain",
                symbol = "CRO",
                code = "CRO"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "CUC",
                name = "Cucumber",
                symbol = "CUC",
                code = "CUC"
            ),
            CurrencyInfo(
                type = TYPE_CRYPTO,
                id = "USDC",
                name = "USD Coin",
                symbol = "USDC",
                code = "USDC"
            )
        )
    }
}
