package com.currencydemoapp.di

import android.content.Context
import androidx.room.Room
import com.currencydemoapp.data.datasource.impl.CryptoLocalDataSource
import com.currencydemoapp.data.datasource.impl.CryptoRemoteDataSource
import com.currencydemoapp.data.datasource.impl.FiatLocalDataSource
import com.currencydemoapp.data.datasource.impl.FiatRemoteDataSource
import com.currencydemoapp.data.db.AppDB
import com.currencydemoapp.domain.repository.impl.DefaultCurrencyRepository
import com.currencydemoapp.domain.usecase.CurrencyUseCase
import com.currencydemoapp.domain.usecase.impl.DefaultCurrencyUseCase
import com.currencydemoapp.presentation.viewmodel.DemoViewModelFactory
import com.currencydemoapp.presentation.viewmodel.ListDataSubject

class AppComponents(private val context: Context) {

    private val database by lazy {
        Room.databaseBuilder(
            context,
            AppDB::class.java,
            "currency_database"
        ).build()
    }

    private val currencyDao by lazy {
        database.currencyInfoDao()
    }

    private val cryptoRemoteDataSource by lazy {
        CryptoRemoteDataSource()
    }

    private val fiatRemoteDataSource by lazy {
        FiatRemoteDataSource()
    }

    private val cryptoLocalDataSource by lazy {
        CryptoLocalDataSource(currencyDao)
    }

    private val fiatLocalDataSource by lazy {
        FiatLocalDataSource(currencyDao)
    }

    private val cryptoRepository by lazy {
        DefaultCurrencyRepository(cryptoRemoteDataSource, cryptoLocalDataSource)
    }

    private val fiatRepository by lazy {
        DefaultCurrencyRepository(fiatRemoteDataSource, fiatLocalDataSource)
    }

    private val currencyUseCase: CurrencyUseCase by lazy {
        DefaultCurrencyUseCase(cryptoRepository, fiatRepository)
    }

    private val listDataSubject by lazy {
        ListDataSubject()
    }

    private val demoViewModelFactory by lazy {
        DemoViewModelFactory(currencyUseCase, listDataSubject)
    }

    fun providesCurrencyUseCase(): CurrencyUseCase = currencyUseCase

    fun providesListDataSubject() = listDataSubject

    fun providesDemoViewModelFactory() = demoViewModelFactory
}
