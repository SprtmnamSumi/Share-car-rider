package cz.muni.fi.pv168.project.business.model;

import cz.muni.fi.pv168.project.ui.model.ListModel;

import java.util.ArrayList;
import java.util.List;

public class Currency extends Entity{
    private String name;

    private String symbol;

    private Double rateToDollar;

    private static Currency chosenCurrency = createDollarCurrency();

    public Currency(String name, String symbol, Double rateToDollar) {
        this.name = name;
        this.symbol = symbol;
        this.rateToDollar = rateToDollar;
    }

    private static Currency createDollarCurrency() {
        return new Currency("dollar", "$", 1.0);
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

    public static Currency getChosenCurrency() {
        return chosenCurrency;
    }

    @Override
    public String toString() {
        return name;
    }
}
