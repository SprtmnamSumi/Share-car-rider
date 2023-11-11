package cz.muni.fi.pv168.project.business.model;

import java.util.Comparator;
import java.util.List;

public class Currency extends Entity {
    private final String name;

    private final String symbol;


    private final List<CurrencyConversion> conversions;

    public Currency(String name, String symbol, List<CurrencyConversion> conversions) {
        this.name = name;
        this.symbol = symbol;
        this.conversions = conversions;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getRateToDollar() {
        return conversions.stream().max(Comparator.comparingInt(CurrencyConversion::getOrder)).get().getConversionRate();
    }

    @Override
    public String toString() {
        return name;
    }

    public List<CurrencyConversion> getConversions() {
        return conversions;
    }
}
