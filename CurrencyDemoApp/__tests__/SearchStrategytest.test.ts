import {
  NameStartsWithSearchStrategy,
  SpacePrefixedSearchStrategy,
  SymbolStartsWithSearchStrategy,
  CompositeSearchStrategy,
} from '../src/utils/SearchStrategy';
import { Currency } from '../src/modules/CurrencyBridgeModule';

const testCurrencies: Currency[] = [
  {
    id: 'BTC',
    name: 'Bitcoin',
    symbol: 'BTC',
    code: 'BTC',
    type: 1,
  },
  {
    id: 'ETH',
    name: 'Ethereum',
    symbol: 'ETH',
    code: 'ETH',
    type: 1,
  },
  {
    id: 'XRP',
    name: 'XRP',
    symbol: 'XRP',
    code: 'XRP',
    type: 1,
  },
  {
    id: 'BCH',
    name: 'Bitcoin Cash',
    symbol: 'BCH',
    code: 'BCH',
    type: 1,
  },
  {
    id: 'LTC',
    name: 'Litecoin',
    symbol: 'LTC',
    code: 'LTC',
    type: 1,
  },
];

describe('NameStartsWithSearchStrategy', () => {
  const strategy = new NameStartsWithSearchStrategy();

  it('should return true for empty search text', () => {
    expect(strategy.match('', testCurrencies[0])).toBe(true);
    expect(strategy.match('   ', testCurrencies[0])).toBe(true);
  });

  it('should match currency name that starts with search term', () => {
    expect(strategy.match('Bit', testCurrencies[0])).toBe(true);
    expect(strategy.match('Bitcoin', testCurrencies[0])).toBe(true);
    expect(strategy.match('Ether', testCurrencies[1])).toBe(true);
  });

  it('should be case insensitive', () => {
    expect(strategy.match('bit', testCurrencies[0])).toBe(true);
    expect(strategy.match('BIT', testCurrencies[0])).toBe(true);
    expect(strategy.match('ether', testCurrencies[1])).toBe(true);
  });

  it('should not match currency name that does not start with search term', () => {
    expect(strategy.match('coin', testCurrencies[0])).toBe(false);
    expect(strategy.match('Cash', testCurrencies[0])).toBe(false);
  });
});

describe('SpacePrefixedSearchStrategy', () => {
  const strategy = new SpacePrefixedSearchStrategy();

  it('should return true for empty search text', () => {
    expect(strategy.match('', testCurrencies[0])).toBe(true);
    expect(strategy.match('   ', testCurrencies[0])).toBe(true);
  });

  it('should match currency name containing space-prefixed search term', () => {
    expect(strategy.match('Cash', testCurrencies[3])).toBe(true);
    expect(strategy.match('cash', testCurrencies[3])).toBe(true);
  });

  it('should not match if search term is not space-prefixed', () => {
    expect(strategy.match('Bitcoin', testCurrencies[3])).toBe(false);
    expect(strategy.match('Bit', testCurrencies[0])).toBe(false);
  });

  it('should not match non-existing terms', () => {
    expect(strategy.match('Dogecoin', testCurrencies[0])).toBe(false);
  });
});

describe('SymbolStartsWithSearchStrategy', () => {
  const strategy = new SymbolStartsWithSearchStrategy();

  it('should return true for empty search text', () => {
    expect(strategy.match('', testCurrencies[0])).toBe(true);
    expect(strategy.match('   ', testCurrencies[0])).toBe(true);
  });

  it('should match currency symbol that starts with search term', () => {
    expect(strategy.match('BT', testCurrencies[0])).toBe(true);
    expect(strategy.match('BTC', testCurrencies[0])).toBe(true);
    expect(strategy.match('ET', testCurrencies[1])).toBe(true);
    expect(strategy.match('X', testCurrencies[2])).toBe(true);
  });

  it('should be case insensitive', () => {
    expect(strategy.match('bt', testCurrencies[0])).toBe(true);
    expect(strategy.match('btc', testCurrencies[0])).toBe(true);
    expect(strategy.match('eth', testCurrencies[1])).toBe(true);
  });

  it('should not match symbol that does not start with search term', () => {
    expect(strategy.match('TC', testCurrencies[0])).toBe(false);
    expect(strategy.match('TH', testCurrencies[1])).toBe(false);
  });
});

describe('CompositeSearchStrategy', () => {
  const nameStrategy = new NameStartsWithSearchStrategy();
  const spacePrefixedStrategy = new SpacePrefixedSearchStrategy();
  const symbolStrategy = new SymbolStartsWithSearchStrategy();
  const compositeStrategy = new CompositeSearchStrategy([
    nameStrategy,
    spacePrefixedStrategy,
    symbolStrategy,
  ]);

  it('should return true for empty search text', () => {
    expect(compositeStrategy.match('', testCurrencies[0])).toBe(true);
    expect(compositeStrategy.match('   ', testCurrencies[0])).toBe(true);
  });

  it('should match if any strategy matches - name starts with', () => {
    expect(compositeStrategy.match('Bit', testCurrencies[0])).toBe(true);
    expect(compositeStrategy.match('Ether', testCurrencies[1])).toBe(true);
  });

  it('should match if any strategy matches - space prefixed', () => {
    expect(compositeStrategy.match('Cash', testCurrencies[3])).toBe(true);
  });

  it('should match if any strategy matches - symbol starts with', () => {
    expect(compositeStrategy.match('BT', testCurrencies[0])).toBe(true);
    expect(compositeStrategy.match('ET', testCurrencies[1])).toBe(true);
  });

  it('should match if multiple strategies match', () => {
    expect(compositeStrategy.match('BTC', testCurrencies[0])).toBe(true); // matches symbol and potentially name
  });

  it('should not match if no strategy matches', () => {
    expect(compositeStrategy.match('DOGE', testCurrencies[0])).toBe(false);
    expect(compositeStrategy.match('xyz', testCurrencies[0])).toBe(false);
  });

  it('should work with empty strategies array', () => {
    const emptyComposite = new CompositeSearchStrategy([]);
    expect(emptyComposite.match('test', testCurrencies[0])).toBe(false);
    expect(emptyComposite.match('', testCurrencies[0])).toBe(true);
  });
});
