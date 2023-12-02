package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;

public interface ICurrencyConverter {
//    CurrencyEntity getDefaultCurrency();


    double convertFromDoolarsToConvRate(double conversionRateToDollars, double amountInDolars);


    double ConvertFromConversionRateTODollars(double conversionRateToGivenCurrewncy, double amountInDolars);

//    void setDefaultCurrency(CurrencyEntity currency);

    double convertFromDolarsToCurrency(Currency wantedCurrency, double amountInDolars);

    double convertFromCurrencyTOdollars(Currency givenCurrency, double amountInGivenCurrency);

    double convertBetwweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency);

//    double convertFromDefaultCurrency(CurrencyEntity wantedCurrency, double amountInGivenCurrency);

//    double convertTodefaultCurrency(CurrencyEntity givenCurrency, double amountInGivenCurrency);

//    double convertFromDolarsToDefaultCurrency(double amountInDolars);

//    double convertFromDefaultCurrencyToDolars(double amountInDefaultCurrency);
}
