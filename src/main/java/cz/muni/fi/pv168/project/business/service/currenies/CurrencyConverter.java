package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.properties.Config;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;

@Singleton
public class CurrencyConverter implements ICurrencyConverter {
    private final TableModel<Currency> currencyListModel;
    private Currency defaultCurrency;

    @Inject
    public CurrencyConverter(TableModel<Currency> currencyListModel) {
        this.currencyListModel = currencyListModel;
    }


    @Override
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    @Override
    public void setDefaultCurrency(Currency currency) {
        Config.saveDefaultCurrency(currency);
        this.defaultCurrency = currency;
    }


    @Override
    public double convertFromDolarsToCurrency(Currency wantedCurrency, double amountInDolars) {
        return amountInDolars * wantedCurrency.getRateToDollar();
    }

    @Override
    public double convertFromCurrencyTOdollars(Currency givenCurrency, double amountInGivenCurrency) {
        return amountInGivenCurrency / givenCurrency.getRateToDollar();
    }

    @Override
    public double convertBetwweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency) {
        double amountInDolars = convertFromCurrencyTOdollars(givenCurrency, amountInGivenCurrency);
        return convertFromDolarsToCurrency(wantedCurrency, amountInDolars);
    }

    @Override
    public double convertFromDefaultCurrency(Currency wantedCurrency, double amountInGivenCurrency) {
        return convertBetwweenCurrencies(defaultCurrency, wantedCurrency, amountInGivenCurrency);
    }

    @Override
    public double convertTodefaultCurrency(Currency givenCurrency, double amountInGivenCurrency) {
        return convertBetwweenCurrencies(givenCurrency, defaultCurrency, amountInGivenCurrency);
    }

    @Override
    public double convertFromDolarsToDefaultCurrency(double amountInDolars) {
        return convertFromDolarsToCurrency(defaultCurrency, amountInDolars);
    }

    @Override
    public double convertFromDefaultCurrencyToDolars(double amountInDefaultCurrency) {
        return convertFromCurrencyTOdollars(defaultCurrency, amountInDefaultCurrency);
    }

    @Override
    public void updateDefaultCurrency() {
        Properties properties = Config.loadProperties();
        var currency = properties.getProperty(Config.PropertiesEnum.CURRENCY_PROPERY.toString());
        var currencies = currencyListModel.getAll();
        var defaultCurrecy = currencies.stream().filter(x -> x.getSymbol().equals(currency)).findFirst().orElseThrow();
        this.defaultCurrency = defaultCurrecy;
    }
}

