package com.currencydemoapp.data.model

import org.junit.Test
import org.junit.Assert.*

class CurrencyInfoTest {

    @Test
    fun `CurrencyInfo should be created with all properties`() {
        // Given
        val type = 1
        val id = "BTC"
        val name = "Bitcoin"
        val symbol = "BTC"
        val code = "BTC"

        // When
        val currencyInfo = CurrencyInfo(type, id, name, symbol, code)

        // Then
        assertEquals(type, currencyInfo.type)
        assertEquals(id, currencyInfo.id)
        assertEquals(name, currencyInfo.name)
        assertEquals(symbol, currencyInfo.symbol)
        assertEquals(code, currencyInfo.code)
    }

    @Test
    fun `CurrencyInfo should handle different currency types`() {
        // Given & When
        val fiatCurrency = CurrencyInfo(2, "USD", "US Dollar", "$", "USD")
        val cryptoCurrency = CurrencyInfo(1, "BTC", "Bitcoin", "BTC", "BTC")

        // Then
        assertEquals(2, fiatCurrency.type)
        assertEquals(1, cryptoCurrency.type)
        assertNotEquals(fiatCurrency.type, cryptoCurrency.type)
    }

    @Test
    fun `CurrencyInfo should support different symbols`() {
        // Given & When
        val dollarCurrency = CurrencyInfo(2, "USD", "US Dollar", "$", "USD")
        val euroCurrency = CurrencyInfo(2, "EUR", "Euro", "€", "EUR")
        val poundCurrency = CurrencyInfo(2, "GBP", "British Pound", "£", "GBP")

        // Then
        assertEquals("$", dollarCurrency.symbol)
        assertEquals("€", euroCurrency.symbol)
        assertEquals("£", poundCurrency.symbol)
    }
}
