package cz.muni.fi.pv168.project.business.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class CarRide extends Template {
    private LocalDateTime date;

    public CarRide(String guid, String Title, String Description, Double Distance, double FuelConsumption, double CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date, Category Category, Currency currency, double newestConversionRate) {
        super(guid, Title, Description, Distance, FuelConsumption, CostOfFuelPerLitre, NumberOfPassengers, commission, Category, currency, newestConversionRate);
        setDate(Date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRide carRide = (CarRide) o;
        return Objects.equals(super.title, carRide.title) &&
                Objects.equals(description, carRide.description)
                && Objects.equals(category, carRide.category)
                && Objects.equals(distance, carRide.distance)
                && Objects.equals(fuelConsumption, carRide.fuelConsumption)
                && Objects.equals(costOfFuelPerLitre, carRide.costOfFuelPerLitre)
                && Objects.equals(numberOfPassengers, carRide.numberOfPassengers)
                && Objects.equals(commission, carRide.commission)
                && Objects.equals(currency, carRide.currency)
                && Objects.equals(newestConversionRateToDollar, carRide.newestConversionRateToDollar)
                && Objects.equals(date, carRide.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, category, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, currency, newestConversionRateToDollar, date);
    }
}
