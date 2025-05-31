package com.currencydemoapp.data.datasource.impl

import TYPE_CRYPTO
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CryptoRemoteDataSourceTest {

    private lateinit var dataSource: CryptoRemoteDataSource

    @Before
    fun setup() {
        dataSource = CryptoRemoteDataSource()
    }

    @Test
    fun `get should return non-empty list of crypto currencies`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        assertFalse("Result should not be empty", result.isEmpty())
        assertTrue("Should contain at least 10 currencies", result.size >= 10)
    }

    @Test
    fun `get should return currencies with correct crypto type`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        result.forEach { currency ->
            assertEquals("All currencies should have CRYPTO type", TYPE_CRYPTO, currency.type)
        }
    }

    @Test
    fun `get should contain expected crypto currencies`() = runTest {
        // When
        val result = dataSource.get()
        val currencyIds = result.map { it.id }

        // Then
        assertTrue("Should contain BTC", currencyIds.contains("BTC"))
        assertTrue("Should contain ETH", currencyIds.contains("ETH"))
        assertTrue("Should contain XRP", currencyIds.contains("XRP"))
        assertTrue("Should contain LTC", currencyIds.contains("LTC"))
    }

    @Test
    fun `get should return currencies with valid structure`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        result.forEach { currency ->
            assertNotNull("ID should not be null", currency.id)
            assertFalse("ID should not be empty", currency.id.isEmpty())
            assertNotNull("Name should not be null", currency.name)
            assertFalse("Name should not be empty", currency.name.isEmpty())
            assertEquals("Symbol should match code for crypto", currency.code, currency.symbol)
        }
    }
}
