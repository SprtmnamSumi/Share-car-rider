package cz.muni.fi.pv168.project.business.service.currenies;

import cz.muni.fi.pv168.project.business.model.Currency;
import javax.inject.Singleton;

@Singleton
public class CurrencyConverter implements ICurrencyConverter {
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
}

