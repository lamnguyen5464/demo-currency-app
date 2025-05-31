package com.currencydemoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.currencydemoapp.domain.usecase.CurrencyUseCase

class DemoViewModelFactory(
    private val useCase: CurrencyUseCase,
    private val listDataSubject: ListDataSubject
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            return DemoViewModel(useCase, listDataSubject) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
