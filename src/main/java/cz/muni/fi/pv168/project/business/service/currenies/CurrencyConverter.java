package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CurrencyConverter implements ICurrencyConverter {
//    private Currency defaultCurrency;

    @Inject
    // TODO
//    TableModel<Currency> currencyListModel
    public CurrencyConverter() {
//        Properties properties = Config.loadProperties();
//        var currency = properties.getProperty(Config.PropertiesEnum.COLOR_THEME_PROPERY.toString());
////        var defaultCurrecy = currencyListModel.getAll().stream().filter(x -> x.getName().equals(currency)).findFirst().orElseThrow();
////        this.defaultCurrency = defaultCurrecy;
//
//        this.defaultCurrency = new Currency("CZK", "Kč", 1.0);
    }

//    @Override
//    public Currency getDefaultCurrency() {
//        return defaultCurrency;
//    }

//    @Override
//    public void setDefaultCurrency(Currency currency) {
//        Config.saveDefaultCurrency(currency);
//        this.defaultCurrency = currency;
//    }


    @Override
    public double convertFromDoolarsToConvRate(double conversionRateToDollars, double amountInDolars) {
        return amountInDolars * conversionRateToDollars;
    }

    @Override
    public double convertFromDolarsToCurrency(Currency wantedCurrency, double amountInDolars) {
        return convertFromDoolarsToConvRate(wantedCurrency.getNewestRateToDollar(), amountInDolars);
    }


    @Override
    public double ConvertFromConversionRateTODollars(double conversionRateToGivenCurrewncy, double amountInCurrency) {
        return amountInCurrency / conversionRateToGivenCurrewncy;
    }

    @Override
    public double convertFromCurrencyTOdollars(Currency givenCurrency, double amountInGivenCurrency) {
        return ConvertFromConversionRateTODollars(givenCurrency.getNewestRateToDollar(), amountInGivenCurrency);
    }

    @Override
    public double convertBetwweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency) {
        double amountInDolars = convertFromCurrencyTOdollars(givenCurrency, amountInGivenCurrency);
        return convertFromDolarsToCurrency(wantedCurrency, amountInDolars);
    }

//    @Override
//    public double convertFromDefaultCurrency(Currency wantedCurrency, double amountInGivenCurrency) {
//        return convertBetwweenCurrencies(defaultCurrency, wantedCurrency, amountInGivenCurrency);
//    }
//
//    @Override
//    public double convertTodefaultCurrency(Currency givenCurrency, double amountInGivenCurrency) {
//        return convertBetwweenCurrencies(givenCurrency, defaultCurrency, amountInGivenCurrency);
//    }
//
//    @Override
//    public double convertFromDolarsToDefaultCurrency(double amountInDolars) {
//        return convertFromDolarsToCurrency(defaultCurrency, amountInDolars);
//    }
//
//    @Override
//    public double convertFromDefaultCurrencyToDolars(double amountInDefaultCurrency) {
//        return convertFromCurrencyTOdollars(defaultCurrency, amountInDefaultCurrency);
//    }
}

