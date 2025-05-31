import { useEffect, useState } from 'react';
import { NativeEventEmitter, NativeModules } from 'react-native';
import { Currency } from '../modules/CurrencyBridgeModule';

const { CurrencyBridgeModule } = NativeModules;
const currencyEventEmitter = new NativeEventEmitter(CurrencyBridgeModule);

const CURRENCY_LIST_CHANGED_EVENT = 'onCurrencyListChanged';

export const useCurrencyData = () => {
  const [currencies, setCurrencies] = useState<Currency[]>([]);

  useEffect(() => {
    const subscription = currencyEventEmitter.addListener(
      CURRENCY_LIST_CHANGED_EVENT,
      (currencyList: Currency[]) => {
        setCurrencies(currencyList);
      },
    );
    CurrencyBridgeModule.startObserving();

    return () => {
      CurrencyBridgeModule.stopObserving();
      subscription.remove();
    };
  }, []);

  return currencies;
};
