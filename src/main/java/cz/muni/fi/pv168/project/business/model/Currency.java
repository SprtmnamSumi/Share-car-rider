package cz.muni.fi.pv168.project.business.model;

public class Currency extends Entity {
    private final String name;

    private final String symbol;

    private Double newestRateToDollar;

    public Currency(String name, String symbol, Double newestRateToDollar) {
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
}
