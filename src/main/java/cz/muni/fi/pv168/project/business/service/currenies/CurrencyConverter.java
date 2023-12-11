package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;
import javax.inject.Singleton;

@Singleton
public class CurrencyConverter implements ICurrencyConverter {
    @Override
    public double convertFromDollarsToConvRate(double conversionRateToDollars, double amountInDollars) {
        return amountInDollars * conversionRateToDollars;
    }

    @Override
    public double convertFromDollarsToCurrency(Currency wantedCurrency, double amountInDollars) {
        return convertFromDollarsToConvRate(wantedCurrency.getNewestRateToDollar(), amountInDollars);
    }


    @Override
    public double ConvertFromConversionRateTODollars(double conversionRateToGivenCurrency, double amountInDollars) {
        return amountInDollars / conversionRateToGivenCurrency;
    }

    @Override
    public double convertFromCurrencyToDollars(Currency givenCurrency, double amountInGivenCurrency) {
        return ConvertFromConversionRateTODollars(givenCurrency.getNewestRateToDollar(), amountInGivenCurrency);
    }

    @Override
    public double convertBetweenCurrencies(Currency givenCurrency, Currency wantedCurrency, double amountInGivenCurrency) {
        double amountInDollars = convertFromCurrencyToDollars(givenCurrency, amountInGivenCurrency);
        return convertFromDollarsToCurrency(wantedCurrency, amountInDollars);
    }
}

