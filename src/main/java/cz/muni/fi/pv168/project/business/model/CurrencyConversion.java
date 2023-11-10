package cz.muni.fi.pv168.project.business.model;

import java.time.LocalDateTime;

public class CurrencyConversion extends Entity {
    private Currency currency;
    private Double conversionRate;
    private LocalDateTime date;
    private int order;

    public CurrencyConversion(Currency currency, Double conversionRate, LocalDateTime date, int order) {
        this.currency = currency;
        this.conversionRate = conversionRate;
        this.date = date;
        this.order = order;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
