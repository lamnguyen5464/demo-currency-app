import React, {useState} from 'react';
import {
  View,
  Text,
  TextInput,
  FlatList,
  StyleSheet,
  StatusBar,
  SafeAreaView,
} from 'react-native';

interface Currency {
  name: string;
  symbol: string;
  code: string;
}

const currencies: Currency[] = [
  {name: 'US Dollar', symbol: '$', code: 'USD'},
  {name: 'Euro', symbol: '€', code: 'EUR'},
  {name: 'British Pound', symbol: '£', code: 'GBP'},
  {name: 'Japanese Yen', symbol: '¥', code: 'JPY'},
  {name: 'Canadian Dollar', symbol: 'C$', code: 'CAD'},
  {name: 'Australian Dollar', symbol: 'A$', code: 'AUD'},
  {name: 'Swiss Franc', symbol: 'CHF', code: 'CHF'},
  {name: 'Chinese Yuan', symbol: '¥', code: 'CNY'},
  {name: 'Indian Rupee', symbol: '₹', code: 'INR'},
  {name: 'South Korean Won', symbol: '₩', code: 'KRW'},
];

const CurrencyScreen: React.FC = () => {
  const [searchText, setSearchText] = useState('');

  const filteredCurrencies = currencies.filter(currency =>
    currency.name.toLowerCase().includes(searchText.toLowerCase()) ||
    currency.code.toLowerCase().includes(searchText.toLowerCase()),
  );

  const renderCurrencyItem = ({item}: {item: Currency}) => (
    <View style={styles.currencyItem}>
      <View style={styles.currencyInfo}>
        <Text style={styles.currencyName}>{item.name}</Text>
        <Text style={styles.currencyCode}>{item.code}</Text>
      </View>
      <Text style={styles.currencySymbol}>{item.symbol}</Text>
    </View>
  );

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />

      <View style={styles.header}>
        <Text style={styles.title}>Currencies</Text>
      </View>

      <View style={styles.searchContainer}>
        <TextInput
          style={styles.searchInput}
          placeholder="Search currencies..."
          value={searchText}
          onChangeText={setSearchText}
          placeholderTextColor="#666"
        />
      </View>

      <FlatList
        data={filteredCurrencies}
        renderItem={renderCurrencyItem}
        keyExtractor={item => item.code}
        style={styles.list}
        showsVerticalScrollIndicator={false}
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
  searchInput: {
    height: 45,
    borderWidth: 1,
    borderColor: '#dee2e6',
    borderRadius: 8,
    paddingHorizontal: 15,
    fontSize: 16,
    backgroundColor: '#f8f9fa',
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
});

export default CurrencyScreen;
