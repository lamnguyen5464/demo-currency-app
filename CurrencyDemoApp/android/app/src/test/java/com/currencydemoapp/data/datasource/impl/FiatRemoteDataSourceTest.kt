package com.currencydemoapp.data.datasource.impl

import TYPE_FIAT
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class FiatRemoteDataSourceTest {

    private lateinit var dataSource: FiatRemoteDataSource

    @Before
    fun setup() {
        dataSource = FiatRemoteDataSource()
    }

    @Test
    fun `get should return non-empty list of fiat currencies`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        assertFalse("Result should not be empty", result.isEmpty())
        assertTrue("Should contain at least 5 currencies", result.size >= 5)
    }

    @Test
    fun `get should return currencies with correct fiat type`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        result.forEach { currency ->
            assertEquals("All currencies should have FIAT type", TYPE_FIAT, currency.type)
        }
    }

    @Test
    fun `get should return currencies with required fields`() = runTest {
        // When
        val result = dataSource.get()

        // Then
        result.forEach { currency ->
            assertNotNull("ID should not be null", currency.id)
            assertFalse("ID should not be empty", currency.id.isEmpty())
            assertNotNull("Name should not be null", currency.name)
            assertFalse("Name should not be empty", currency.name.isEmpty())
            assertNotNull("Symbol should not be null", currency.symbol)
            assertFalse("Symbol should not be empty", currency.symbol.isEmpty())
            assertNotNull("Code should not be null", currency.code)
            assertFalse("Code should not be empty", currency.code.isEmpty())
        }
    }

    @Test
    fun `get should contain expected fiat currencies`() = runTest {
        // When
        val result = dataSource.get()
        val currencyIds = result.map { it.id }

        // Then
        assertTrue("Should contain USD", currencyIds.contains("USD"))
        assertTrue("Should contain EUR", currencyIds.contains("EUR"))
        assertTrue("Should contain SGD", currencyIds.contains("SGD"))
        assertTrue("Should contain GBP", currencyIds.contains("GBP"))
        assertTrue("Should contain JPY", currencyIds.contains("JPY"))
    }
}
