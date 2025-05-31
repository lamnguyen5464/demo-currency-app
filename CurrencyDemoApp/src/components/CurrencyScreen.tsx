import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  TextInput,
  FlatList,
  StyleSheet,
  StatusBar,
  SafeAreaView,
  TouchableOpacity,
  Keyboard,
} from 'react-native';
import { useCurrencyData } from '../hooks/useCurrencyData';
import { Currency } from '../modules/CurrencyBridgeModule';
import {
  CompositeSearchStrategy,
  NameStartsWithSearchStrategy,
  SpacePrefixedSearchStrategy,
  SymbolStartsWithSearchStrategy,
} from '../utils/SearchStrategy';

const searchStrategy = new CompositeSearchStrategy([
  new NameStartsWithSearchStrategy(),
  new SpacePrefixedSearchStrategy(),
  new SymbolStartsWithSearchStrategy(),
]);

const CurrencyScreen: React.FC = () => {
  const [searchText, setSearchText] = useState('');
  const currencies = useCurrencyData();

  useEffect(() => {
    clearSearch();
  }, [currencies]);

  const filteredCurrencies = currencies.filter(currency =>
    searchStrategy.match(searchText, currency),
  );

  const clearSearch = () => {
    setSearchText('');
    Keyboard.dismiss();
  };

  const renderCurrencyItem = ({ item }: { item: Currency }) => (
    <View style={styles.currencyItem}>
      <View style={styles.leadingView}>
        <Text style={styles.leadingText}>{item.code.charAt(0)}</Text>
      </View>
      <View style={styles.currencyInfo}>
        <Text style={styles.currencyName}>{item.name}</Text>
        <Text style={styles.currencyCode}>{item.code}</Text>
      </View>
    </View>
  );

  const renderFooter = () => (
    <View style={styles.footer}>
      <Text style={styles.footerText}>
        Showing {filteredCurrencies.length} of {currencies.length} currencies
      </Text>
    </View>
  );

  const renderEmptyComponent = () => (
    <View style={styles.emptyContainer}>
      <Text style={styles.emptyText}>No currencies found</Text>
      {searchText ? (
        <Text style={styles.emptySubText}>Try adjusting your search</Text>
      ) : null}
    </View>
  );

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />

      <View style={styles.searchContainer}>
        <View style={styles.searchInputContainer}>
          <TextInput
            style={[
              styles.searchInput,
              searchText ? styles.searchInputWithButton : null,
            ]}
            placeholder="Search currencies..."
            value={searchText}
            onChangeText={setSearchText}
            placeholderTextColor="#666"
          />
          {searchText ? (
            <TouchableOpacity style={styles.closeButton} onPress={clearSearch}>
              <Text style={styles.closeButtonText}>✕</Text>
            </TouchableOpacity>
          ) : null}
        </View>
      </View>

      <FlatList
        data={filteredCurrencies}
        renderItem={renderCurrencyItem}
        keyExtractor={item => item.code}
        style={styles.list}
        showsVerticalScrollIndicator={false}
        ListFooterComponent={
          filteredCurrencies.length > 0 ? renderFooter : null
        }
        ListEmptyComponent={renderEmptyComponent}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f8f9fa',
  },
  header: {
    paddingHorizontal: 20,
    paddingVertical: 15,
    backgroundColor: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#e9ecef',
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#212529',
  },
  searchContainer: {
    paddingHorizontal: 20,
    paddingVertical: 15,
    backgroundColor: '#fff',
  },
  searchInputContainer: {
    position: 'relative',
  },
  searchInput: {
    height: 45,
    borderWidth: 1,
    borderColor: '#dee2e6',
    borderRadius: 8,
    paddingHorizontal: 15,
    fontSize: 16,
    backgroundColor: '#f8f9fa',
  },
  searchInputWithButton: {
    paddingRight: 45,
  },
  closeButton: {
    position: 'absolute',
    right: 8,
    top: 8,
    width: 29,
    height: 29,
    borderRadius: 14.5,
    backgroundColor: '#6c757d',
    justifyContent: 'center',
    alignItems: 'center',
  },
  closeButtonText: {
    color: '#fff',
    fontSize: 14,
    fontWeight: 'bold',
  },
  list: {
    flex: 1,
    paddingTop: 10,
  },
  currencyItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 15,
    paddingHorizontal: 20,
    marginHorizontal: 15,
    marginVertical: 5,
    backgroundColor: '#fff',
    borderRadius: 8,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.1,
    shadowRadius: 2,
    elevation: 2,
  },
  currencyInfo: {
    flex: 1,
    marginLeft: 12,
  },
  leadingView: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: '#007bffaa',
    justifyContent: 'center',
    alignItems: 'center',
  },
  leadingText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  currencyName: {
    fontSize: 16,
    fontWeight: '600',
    color: '#212529',
    marginBottom: 4,
  },
  currencyCode: {
    fontSize: 14,
    color: '#6c757d',
  },
  currencySymbol: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#007bff',
  },
  footer: {
    paddingVertical: 20,
    paddingHorizontal: 20,
    alignItems: 'center',
  },
  footerText: {
    fontSize: 14,
    color: '#6c757d',
    fontStyle: 'italic',
  },
  emptyContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 60,
  },
  emptyText: {
    fontSize: 18,
    fontWeight: '600',
    color: '#6c757d',
    marginBottom: 8,
  },
  emptySubText: {
    fontSize: 14,
    color: '#adb5bd',
  },
});

export default CurrencyScreen;
