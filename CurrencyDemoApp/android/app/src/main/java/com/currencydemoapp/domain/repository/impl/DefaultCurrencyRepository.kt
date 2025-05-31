package com.currencydemoapp.domain.repository.impl

import com.currencydemoapp.data.datasource.CurrencyLocalDataSource
import com.currencydemoapp.data.datasource.CurrencyRemoteDataSource
import com.currencydemoapp.data.model.CurrencyInfo
import com.currencydemoapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultCurrencyRepository(
    private val remoteDataSource: CurrencyRemoteDataSource,
    private val localDataSource: CurrencyLocalDataSource,
) : CurrencyRepository {

    private val mutex = Mutex()

    override suspend fun fetch(): List<CurrencyInfo> {
        mutex.withLock {
            val data = remoteDataSource.get()
            localDataSource.set(data)
            return data
        }
    }


    override suspend fun get(): List<CurrencyInfo> {
        return mutex.withLock {
            localDataSource.get()
        }
    }

    override suspend fun clear() {
        mutex.withLock {
            localDataSource.clear()
        }
    }
}
