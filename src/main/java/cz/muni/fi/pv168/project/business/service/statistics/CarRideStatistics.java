package cz.muni.fi.pv168.project.business.service.statistics;

import cz.muni.fi.pv168.project.business.model.CarRide;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public abstract class CarRideStatistics {

    public static Double getTotalDistance(List<CarRide> carRides) {
        return carRides.stream().map(CarRide::getDistance).mapToDouble(d -> d).sum();
    }

    private static Double getCarRideCost(CarRide carRide) {
        return carRide.getDistance() * carRide.getFuelConsumption()/100 * carRide.getCostOfFuelPerLitre();
    }
    public static Double getTotalExpenses(List<CarRide> carRides) {
        var totalExpenses = 0.0;
        for (CarRide carRide : carRides) {
            totalExpenses += getCarRideCost(carRide);
        }
        return totalExpenses;
    }

    public static Double getTodayExpenses(List<CarRide> carRides) {
        var todayExpenses = 0.0;
        for (CarRide carRide : carRides) {
            if (carRide.getDate().toLocalDate().isEqual(LocalDate.now(ZoneId.systemDefault()))) {
                todayExpenses += getCarRideCost(carRide);
            }
        }
        return todayExpenses;
    }

    public static Double getTotalRevenues(List<CarRide> carRides) {
        var totalRevenues = 0.0;
        for (CarRide carRide : carRides) {
                totalRevenues += getCarRideCost(carRide) * (1 + carRide.getCommission()/100);
        }
        return totalRevenues;
    }

    public static Integer getTotalRides(List<CarRide> carRides) {
        return carRides.size();
    }
}
