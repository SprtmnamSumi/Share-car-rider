package cz.muni.fi.pv168.project.storage.sql.entity;

import java.time.LocalDateTime;

public class CarRideEntity extends TemplateEntity {

    private LocalDateTime date;

    public CarRideEntity(Long id, String guid, String title, String description, Double distance, double fuelConsumption,
                         double costOfFuelPerLitre, int numberOfPassengers, double commission,
                         Long category, Long currency, LocalDateTime date) {
        super(id, guid, title, description, distance, fuelConsumption, costOfFuelPerLitre, numberOfPassengers, commission, category, currency);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

