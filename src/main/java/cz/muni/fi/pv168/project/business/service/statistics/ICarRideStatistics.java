package cz.muni.fi.pv168.project.business.service.statistics;

import cz.muni.fi.pv168.project.business.model.CarRide;

import java.util.List;

public interface ICarRideStatistics {
    Double getTotalDistance(List<CarRide> carRides);

    default Double getCarRideCost(CarRide carRide) {
        return carRide.getDistance() * carRide.getFuelConsumption() / 100 * carRide.getCostOfFuelPerLitre();
    }

    Double getTotalExpenses(List<CarRide> carRides);

    Double getTodayExpenses(List<CarRide> carRides);

    Double getTotalRevenues(List<CarRide> carRides);

    Integer getTotalRides(List<CarRide> carRides);
}
