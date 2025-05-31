package com.currencydemoapp.domain.repository.impl

import com.currencydemoapp.data.datasource.CurrencyLocalDataSource
import com.currencydemoapp.data.datasource.CurrencyRemoteDataSource
import com.currencydemoapp.data.model.CurrencyInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultCurrencyRepositoryTest {

    private lateinit var repository: DefaultCurrencyRepository
    private lateinit var mockRemoteDataSource: MockCurrencyRemoteDataSource
    private lateinit var mockLocalDataSource: MockCurrencyLocalDataSource

    @Before
    fun setUp() {
        mockRemoteDataSource = MockCurrencyRemoteDataSource()
        mockLocalDataSource = MockCurrencyLocalDataSource()
        repository = DefaultCurrencyRepository(mockRemoteDataSource, mockLocalDataSource)
    }

    @Test
    fun `fetch should get data from remote and save to local`() = runBlocking {
        // Given
        val expectedData = listOf(
            CurrencyInfo(1, "USD", "US Dollar", "$", "USD"),
            CurrencyInfo(1, "EUR", "Euro", "€", "EUR")
        )
        mockRemoteDataSource.mockData = expectedData

        // When
        val result = repository.fetch()

        // Then
        assertEquals(expectedData, result)
        assertTrue(mockRemoteDataSource.getCalled)
        assertTrue(mockLocalDataSource.setCalled)
        assertEquals(expectedData, mockLocalDataSource.storedData)
    }

    @Test
    fun `get should return data from local data source`() = runBlocking {
        // Given
        val expectedData = listOf(
            CurrencyInfo(1, "GBP", "British Pound", "£", "GBP")
        )
        mockLocalDataSource.mockData = expectedData

        // When
        val result = repository.get()

        // Then
        assertEquals(expectedData, result)
        assertTrue(mockLocalDataSource.getCalled)
        assertFalse(mockRemoteDataSource.getCalled)
    }

    @Test
    fun `clear should call local data source clear`() = runBlocking {
        // When
        repository.clear()

        // Then
        assertTrue(mockLocalDataSource.clearCalled)
    }

    @Test
    fun `fetch should handle empty data from remote`() = runBlocking {
        // Given
        mockRemoteDataSource.mockData = emptyList()

        // When
        val result = repository.fetch()

        // Then
        assertEquals(emptyList<CurrencyInfo>(), result)
        assertTrue(mockRemoteDataSource.getCalled)
        assertTrue(mockLocalDataSource.setCalled)
        assertEquals(emptyList<CurrencyInfo>(), mockLocalDataSource.storedData)
    }

    @Test
    fun `get should handle empty data from local`() = runBlocking {
        // Given
        mockLocalDataSource.mockData = emptyList()

        // When
        val result = repository.get()

        // Then
        assertEquals(emptyList<CurrencyInfo>(), result)
        assertTrue(mockLocalDataSource.getCalled)
    }

    // Manual mock classes
    private class MockCurrencyRemoteDataSource : CurrencyRemoteDataSource {
        var mockData: List<CurrencyInfo> = emptyList()
        var getCalled = false

        override suspend fun get(): List<CurrencyInfo> {
            getCalled = true
            return mockData
        }
    }

    private class MockCurrencyLocalDataSource : CurrencyLocalDataSource {
        var mockData: List<CurrencyInfo> = emptyList()
        var storedData: List<CurrencyInfo> = emptyList()
        var getCalled = false
        var setCalled = false
        var clearCalled = false

        override suspend fun get(): List<CurrencyInfo> {
            getCalled = true
            return mockData
        }

        override suspend fun set(data: List<CurrencyInfo>) {
            setCalled = true
            storedData = data
        }

        override suspend fun clear() {
            clearCalled = true
            storedData = emptyList()
        }
    }
}
