package com.currencydemoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencydemoapp.domain.usecase.CurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DemoViewModel(
    private val useCase: CurrencyUseCase,
    private val listDataSubject: ListDataSubject
) : ViewModel() {

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.clearAll()
            listDataSubject.emit(emptyList())
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.refresh()
            val data = useCase.getAll()
            listDataSubject.emit(data)
        }
    }

    fun useCryptoList() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = useCase.getCryptoList()
            listDataSubject.emit(data)
        }
    }

    fun useFiatList() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = useCase.getFiatList()
            listDataSubject.emit(data)
        }
    }

    fun useAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = useCase.getAll()
            listDataSubject.emit(data)
        }
    }
}