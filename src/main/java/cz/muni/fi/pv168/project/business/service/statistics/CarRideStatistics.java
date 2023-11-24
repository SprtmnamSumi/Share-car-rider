package cz.muni.fi.pv168.project.business.service.statistics;

import com.google.inject.Inject;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.service.currenies.ICurrencyConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class CarRideStatistics implements ICarRideStatistics {
    private final ICurrencyConverter ICurrencyConverter;

    @Inject
    public CarRideStatistics(ICurrencyConverter ICurrencyConverter) {
        this.ICurrencyConverter = ICurrencyConverter;
    }

    @Override
    public Double getTotalDistance(List<CarRide> carRides) {
        return carRides.stream().map(CarRide::getDistance).mapToDouble(d -> d).sum();
    }

    @Override
    public Double getTotalExpenses(List<CarRide> carRides) {
        var totalExpenses = 0.0;
        for (CarRide carRide : carRides) {
            totalExpenses += getCarRideCost(carRide);
        }
        return totalExpenses;
//        return ICurrencyConverter.convertFromDolarsToDefaultCurrency(totalExpenses);
    }

    @Override
    public Double getTodayExpenses(List<CarRide> carRides) {
        var todayExpenses = 0.0;
        for (CarRide carRide : carRides) {
            if (carRide.getDate().toLocalDate().isEqual(LocalDate.now(ZoneId.systemDefault()))) {
                todayExpenses += getCarRideCost(carRide);
            }
        }
        return todayExpenses;
//        return ICurrencyConverter.convertFromDolarsToDefaultCurrency(todayExpenses);
    }

    @Override
    public Double getTotalRevenues(List<CarRide> carRides) {
        var totalRevenues = 0.0;
        for (CarRide carRide : carRides) {
            totalRevenues += getCarRideCost(carRide) * (1 + carRide.getCommission() / 100);
        }
        return totalRevenues;
//        return ICurrencyConverter.convertFromDolarsToDefaultCurrency(totalRevenues);
    }

    @Override
    public Integer getTotalRides(List<CarRide> carRides) {
        return carRides.size();
    }
}
