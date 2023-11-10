package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;

public interface ICurrencyConverter {
    Currency getDefaultCurrency();

    void setDefaultCurrency(Currency currency);

    double convertFromDolarsToCurrency(Currency wantedCurrency, double amountInDolars);

    double convertFromCurrencyTOdollars(Currency givenCurrency, double amountInGivenCurrency);

    double convertBetwweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency);

    double convertFromDefaultCurrency(Currency wantedCurrency, double amountInGivenCurrency);

    double convertTodefaultCurrency(Currency givenCurrency, double amountInGivenCurrency);

    double convertFromDolarsToDefaultCurrency(double amountInDolars);

    double convertFromDefaultCurrencyToDolars(double amountInDefaultCurrency);
}
