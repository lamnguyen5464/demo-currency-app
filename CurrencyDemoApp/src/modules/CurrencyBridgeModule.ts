export interface Currency {
  type: number;
  id: string;
  name: string;
  symbol: string;
  code: string;
}

export interface CurrencyModuleInterface {
  startObserving(): void;
  stopObserving(): void;
}
