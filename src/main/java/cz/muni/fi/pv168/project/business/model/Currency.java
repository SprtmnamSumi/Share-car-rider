package cz.muni.fi.pv168.project.business.model;

import java.util.List;

public class Currency extends Entity {
    private final String name;

    private final String symbol;

    private final Double rateToDollar;
    private final List<CurrencyConversion> conversions;

    public Currency(String name, String symbol, Double rateToDollar, List<CurrencyConversion> conversions) {
        this.name = name;
        this.symbol = symbol;
        this.rateToDollar = rateToDollar;
        this.conversions = conversions;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getRateToDollar() {
        return rateToDollar;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<CurrencyConversion> getConversions() {
        return conversions;
    }
}
