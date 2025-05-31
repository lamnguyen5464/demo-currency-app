package com.currencydemoapp.presentation.jsbridge

import com.currencydemoapp.data.model.CurrencyInfo
import com.currencydemoapp.presentation.viewmodel.ListDataSubject
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CurrencyBridgeModule(
    reactContext: ReactApplicationContext,
    private val listDataSubject: ListDataSubject
) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        private const val MODULE_NAME = "CurrencyBridgeModule"
        private const val EVENT_CURRENCY_LIST_CHANGED = "onCurrencyListChanged"
        private const val KEY_TYPE = "type"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_SYMBOL = "symbol"
        private const val KEY_CODE = "code"
    }

    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private var observingJob: Job? = null

    override fun getName(): String {
        return MODULE_NAME
    }

    private fun observeListData() {
        observingJob = listDataSubject
            .observe()
            .onEach { currencies ->
                emitCurrencyListEvent(currencies)
            }
            .launchIn(scope)
    }

    private fun emitCurrencyListEvent(currencies: List<CurrencyInfo>) {
        val params = Arguments.createArray()

        currencies.forEach { currency ->
            val currencyMap = Arguments.createMap().apply {
                putInt(KEY_TYPE, currency.type)
                putString(KEY_ID, currency.id)
                putString(KEY_NAME, currency.name)
                putString(KEY_SYMBOL, currency.symbol)
                putString(KEY_CODE, currency.code)
            }
            params.pushMap(currencyMap)
        }

        reactApplicationContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(EVENT_CURRENCY_LIST_CHANGED, params)
    }

    @ReactMethod
    fun startObserving() {
        observeListData()
    }

    @ReactMethod
    fun stopObserving() {
        observingJob?.cancel()
    }
}
