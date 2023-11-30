package cz.muni.fi.pv168.project.storage.sql.entity;

import java.time.LocalDateTime;


public record CarRideEntity(String guid, String Title, String Description, Double Distance, double FuelConsumption,
                            double CostOfFuelPerLitre, int NumberOfPassengers, double commission, LocalDateTime Date,
                            int category, Currency currency, double newestConversionRate) {

}
