package cz.muni.fi.pv168.project.business.model;

import java.util.Objects;

public class Currency extends Model {
    private String name;
    private String symbol;
    private Double newestRateToDollar;

    public Currency(String guid, String name, String symbol, Double newestRateToDollar) {
        super(guid);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
