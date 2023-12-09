package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;

public interface ICurrencyConverter {
    double convertFromDoolarsToConvRate(double conversionRateToDollars, double amountInDolars);

    double ConvertFromConversionRateTODollars(double conversionRateToGivenCurrewncy, double amountInDolars);

    double convertFromDolarsToCurrency(Currency wantedCurrency, double amountInDolars);

    double convertFromCurrencyTOdollars(Currency givenCurrency, double amountInGivenCurrency);

    double convertBetwweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency);
}
