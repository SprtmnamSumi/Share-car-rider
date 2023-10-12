package cz.muni.fi.pv168.project.entities;

import java.time.LocalDateTime;

public class CarRide extends CarRideTemplate {
    private LocalDateTime date;

    public CarRide(String Title, String Description, Double Distance, double FuelConsumption, int CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date, Category Category) {
        super(Title, Description, Distance, FuelConsumption, CostOfFuelPerLitre, NumberOfPassengers, commission, Category);
        setDate(Date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
