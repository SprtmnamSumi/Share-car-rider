package cz.muni.fi.pv168.project.business.model;

public class Currency extends Entity {
    private final String name;

    private final String symbol;

    private final Double rateToDollar;
    
    public Currency(String name, String symbol, Double rateToDollar) {
        this.name = name;
        this.symbol = symbol;
        this.rateToDollar = rateToDollar;
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
}
