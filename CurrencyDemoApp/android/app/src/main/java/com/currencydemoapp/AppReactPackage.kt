package com.currencydemoapp


import com.currencydemoapp.di.AppComponents
import com.currencydemoapp.presentation.jsbridge.CurrencyBridgeModule
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class AppReactPackage(
    private val appComponents: AppComponents
) : ReactPackage {

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(
            CurrencyBridgeModule(
                reactContext,
                appComponents.providesListDataSubject()
            )
        )
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}