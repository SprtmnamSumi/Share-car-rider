package cz.muni.fi.pv168.project.storage.sql.entity;

public class CurrencyEntity extends Entity {
    private final String name;
    private final String symbol;
    private Double newestRateToDollar;

    public CurrencyEntity(Long id, String guid, String name, String symbol, Double newestRateToDollar) {
        super(id, guid);
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
}
