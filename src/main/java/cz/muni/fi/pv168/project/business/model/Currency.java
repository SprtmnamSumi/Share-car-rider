package cz.muni.fi.pv168.project.business.model;

import java.util.Objects;

public class Currency extends Entity {
    private final String name;

    private final String symbol;

    private Double newestRateToDollar;

    public Currency(String name, String symbol, Double newestRateToDollar) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.newestRateToDollar = newestRateToDollar;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getNewestRateToDollar() {
        return newestRateToDollar;
    }

    public void setNewestRateToDollar(double newestRateToDollar) {
        this.newestRateToDollar = newestRateToDollar;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency currency)) return false;

        if (!name.equals(currency.name)) return false;
        if (!symbol.equals(currency.symbol)) return false;
        return newestRateToDollar.equals(currency.newestRateToDollar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, symbol, newestRateToDollar);
    }
}
