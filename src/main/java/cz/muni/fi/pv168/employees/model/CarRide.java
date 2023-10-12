package cz.muni.fi.pv168.employees.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CarRide {

    private String title;
    private String description;
    private double distance;
    private double fuelConsumption;
    private double costOfFuelPerLitre;
    private int numberOfPassengers;
    private double commission;
    private LocalDateTime date;
    private Category category;

    public CarRide(String Title, String Description, int Distance, double FuelConsumption, int CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date, Category Category) {
        setTitle(Title);
        setDescription(Description);
        setDistance(Distance);
        setFuelConsumption(FuelConsumption);
        setCostOfFuelPerLitre(CostOfFuelPerLitre);
        setNumberOfPassengers(NumberOfPassengers);
        setCommission(commission);
        setDate(Date);
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public double getCostOfFuelPerLitre() {
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
}
