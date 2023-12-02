package cz.muni.fi.pv168.project.storage.sql.entity;

import java.util.Currency;

public class TemplateEntity extends Entity {
 
    private String title;
    private String description;
    private Double distance;
    private double fuelConsumption;
    private double costOfFuelPerLitre;
    private int numberOfPassengers;
    private double commission;

    private int category;
    private Currency currency;
    private double newestConversionRate;

    public TemplateEntity(Long id, String guid, String title, String description, Double distance, double fuelConsumption,
                          double costOfFuelPerLitre, int numberOfPassengers, double commission,
                          int category, Currency currency, double newestConversionRate) {
        super(id, guid);

        this.title = title;
        this.description = description;
        this.distance = distance;
        this.fuelConsumption = fuelConsumption;
        this.costOfFuelPerLitre = costOfFuelPerLitre;
        this.numberOfPassengers = numberOfPassengers;
        this.commission = commission;

        this.category = category;
        this.currency = currency;
        this.newestConversionRate = newestConversionRate;
    }

    // Add getters and setters for each field


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getCostOfFuelPerLitre() {
        return costOfFuelPerLitre;
    }

    public void setCostOfFuelPerLitre(double costOfFuelPerLitre) {
        this.costOfFuelPerLitre = costOfFuelPerLitre;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getNewestConversionRate() {
        return newestConversionRate;
    }

    public void setNewestConversionRate(double newestConversionRate) {
        this.newestConversionRate = newestConversionRate;
    }
}

