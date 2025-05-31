import { Currency } from '../modules/CurrencyBridgeModule';

export interface SearchStrategy {
  match(searchText: string, currency: Currency): boolean;
}

export class NameStartsWithSearchStrategy implements SearchStrategy {
  match(searchText: string, currency: Currency): boolean {
    if (!searchText.trim()) {
      return true;
    }
    return currency.name.toLowerCase().startsWith(searchText.toLowerCase());
  }
}

export class SpacePrefixedSearchStrategy implements SearchStrategy {
  match(searchText: string, currency: Currency): boolean {
    if (!searchText.trim()) {
      return true;
    }
    const spacePrefixedTerm = ` ${searchText.toLowerCase()}`;
    return currency.name.toLowerCase().includes(spacePrefixedTerm);
  }
}

export class SymbolStartsWithSearchStrategy implements SearchStrategy {
  match(searchText: string, currency: Currency): boolean {
    if (!searchText.trim()) {
      return true;
    }
    return currency.symbol.toLowerCase().startsWith(searchText.toLowerCase());
  }
}

export class CompositeSearchStrategy implements SearchStrategy {
  private strategies: SearchStrategy[];

  constructor(strategies: SearchStrategy[]) {
    this.strategies = strategies;
  }

  match(searchText: string, currency: Currency): boolean {
    if (!searchText.trim()) {
      return true;
    }

    return this.strategies.some(strategy =>
      strategy.match(searchText, currency),
    );
  }
}
