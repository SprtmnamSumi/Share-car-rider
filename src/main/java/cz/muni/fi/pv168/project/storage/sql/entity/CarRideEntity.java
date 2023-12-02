package cz.muni.fi.pv168.project.storage.sql.entity;

import java.time.LocalDateTime;
import java.util.Currency;

public class CarRideEntity extends TemplateEntity {

    private LocalDateTime date;

    public CarRideEntity(Long id, String guid, String title, String description, Double distance, double fuelConsumption,
                         double costOfFuelPerLitre, int numberOfPassengers, double commission, LocalDateTime date,
                         int category, Currency currency, double newestConversionRate) {
        super(id, guid, title, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency, newestConversionRate);
        this.date = date;

    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

