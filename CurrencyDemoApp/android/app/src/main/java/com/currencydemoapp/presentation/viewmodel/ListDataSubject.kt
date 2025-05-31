package com.currencydemoapp.presentation.viewmodel

import com.currencydemoapp.data.model.CurrencyInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListDataSubject {
    private val state = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    suspend fun emit(data: List<CurrencyInfo>) {
        state.emit(data)
    }

    fun observe(): StateFlow<List<CurrencyInfo>> {
        return state.asStateFlow()
    }
}