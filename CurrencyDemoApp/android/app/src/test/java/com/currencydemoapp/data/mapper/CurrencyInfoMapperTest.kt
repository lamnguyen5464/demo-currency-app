package com.currencydemoapp.data.mapper

import com.currencydemoapp.data.db.entity.CurrencyInfoEntity
import com.currencydemoapp.data.model.CurrencyInfo
import org.junit.Test
import org.junit.Assert.*

class CurrencyInfoMapperTest {

    @Test
    fun `toEntity should convert CurrencyInfo to CurrencyInfoEntity correctly`() {
        // Given
        val currencyInfo = CurrencyInfo(
            type = 1,
            id = "BTC",
            name = "Bitcoin",
            symbol = "BTC",
            code = "BTC"
        )

        // When
        val entity = currencyInfo.toEntity()

        // Then
        assertEquals(currencyInfo.type, entity.type)
        assertEquals(currencyInfo.id, entity.id)
        assertEquals(currencyInfo.name, entity.name)
        assertEquals(currencyInfo.symbol, entity.symbol)
        assertEquals(currencyInfo.code, entity.code)
    }

    @Test
    fun `toModel should convert CurrencyInfoEntity to CurrencyInfo correctly`() {
        // Given
        val entity = CurrencyInfoEntity(
            type = 2,
            id = "USD",
            name = "US Dollar",
            symbol = "$",
            code = "USD"
        )

        // When
        val currencyInfo = entity.toModel()

        // Then
        assertEquals(entity.type, currencyInfo.type)
        assertEquals(entity.id, currencyInfo.id)
        assertEquals(entity.name, currencyInfo.name)
        assertEquals(entity.symbol, currencyInfo.symbol)
        assertEquals(entity.code, currencyInfo.code)
    }

    @Test
    fun `mapping should be bidirectional`() {
        // Given
        val originalCurrency = CurrencyInfo(
            type = 1,
            id = "ETH",
            name = "Ethereum",
            symbol = "ETH",
            code = "ETH"
        )

        // When
        val convertedBack = originalCurrency.toEntity().toModel()

        // Then
        assertEquals(originalCurrency.type, convertedBack.type)
        assertEquals(originalCurrency.id, convertedBack.id)
        assertEquals(originalCurrency.name, convertedBack.name)
        assertEquals(originalCurrency.symbol, convertedBack.symbol)
        assertEquals(originalCurrency.code, convertedBack.code)
    }
}
