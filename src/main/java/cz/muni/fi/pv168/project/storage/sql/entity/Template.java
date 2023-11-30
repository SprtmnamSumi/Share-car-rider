package cz.muni.fi.pv168.project.storage.sql.entity;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Entity;
import java.util.Objects;

public class Template extends Entity {
    protected Currency currency;
    protected String title;
    protected String description;
    protected double distance;
    protected double fuelConsumption;
    protected double costOfFuelPerLitre;
    protected int numberOfPassengers;
    protected double commission;
    protected Category category;

    protected double newestConversionRateToDollar;

    public Template(String guid, String Title, String Description, Double Distance, double FuelConsumption, double CostOfFuelPerLitre, int NumberOfPassengers, double commission, Category Category, Currency currency, double newestConversionRateToDollar) {
        super(guid);
        this.currency = currency;
        this.newestConversionRateToDollar = newestConversionRateToDollar;
        setTitle(Title);
        setDescription(Description);
        setDistance(Distance);
        setFuelConsumption(FuelConsumption);
        setCostOfFuelPerLitre(CostOfFuelPerLitre);
        setNumberOfPassengers(NumberOfPassengers);
        setCommission(commission);

        setCategory(Category);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "title must not be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "description must not be null");
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    @Override
    public String toString() {
        return title + ' ' + description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getCostOfFuelPerLitreInDollars() {
        return costOfFuelPerLitre;
    }

    public void setCostOfFuelPerLitre(double costOfFuelPerLitre) {
        this.costOfFuelPerLitre = costOfFuelPerLitre;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(title, template.title) &&
                Objects.equals(description, template.description)
                && Objects.equals(category, template.category)
                && Objects.equals(distance, template.distance)
                && Objects.equals(fuelConsumption, template.fuelConsumption)
                && Objects.equals(costOfFuelPerLitre, template.costOfFuelPerLitre)
                && Objects.equals(numberOfPassengers, template.numberOfPassengers)
                && Objects.equals(commission, template.commission)
                && Objects.equals(currency, template.currency)
                && Objects.equals(newestConversionRateToDollar, template.newestConversionRateToDollar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, category, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, currency, newestConversionRateToDollar);
    }


    public double getConversionToDollars() {
        return newestConversionRateToDollar;
    }

    public void setConversionRateToDollar(double newestConversionRateToDollar) {
        this.newestConversionRateToDollar = newestConversionRateToDollar;
    }
}
