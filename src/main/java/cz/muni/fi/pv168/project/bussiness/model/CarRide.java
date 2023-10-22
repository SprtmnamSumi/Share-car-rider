package cz.muni.fi.pv168.project.bussiness.model;

import java.time.LocalDateTime;

public class CarRide extends Template {
    private LocalDateTime date;

    public CarRide(String guid, String Title, String Description, Double Distance, double FuelConsumption, int CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date, Category Category) {
        super(guid, Title, Description, Distance, FuelConsumption, CostOfFuelPerLitre, NumberOfPassengers, commission, Category);
        setDate(Date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
