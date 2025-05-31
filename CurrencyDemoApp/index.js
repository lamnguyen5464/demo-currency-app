import { AppRegistry } from 'react-native';
import App from './src/App';
import CurrencyScreen from './src/components/CurrencyScreen';
import app from './app.json';

AppRegistry.registerComponent(app.name, () => App);

AppRegistry.registerComponent(app.screens.currency.name, () => CurrencyScreen);
