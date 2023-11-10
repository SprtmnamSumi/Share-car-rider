package cz.muni.fi.pv168.project.business.model;

import java.time.LocalDateTime;


public class CarRide extends Template {
    private LocalDateTime date;

    public CarRide(String guid, String Title, String Description, Double Distance, double FuelConsumption, double CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date, Category Category, Currency currency) {
        super(guid, Title, Description, Distance, FuelConsumption, CostOfFuelPerLitre, NumberOfPassengers, commission, Category, currency);
        setDate(Date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
