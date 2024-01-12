package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;

public interface ICurrencyConverter {
    double convertFromDollarsToConvRate(double conversionRateToDollars, double amountInDollars);

    double ConvertFromConversionRateTODollars(double conversionRateToGivenCurrency, double amountInDollars);

    double convertFromDollarsToCurrency(Currency wantedCurrency, double amountInDollars);

    double convertFromCurrencyToDollars(Currency givenCurrency, double amountInGivenCurrency);

    double convertBetweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency);
}
