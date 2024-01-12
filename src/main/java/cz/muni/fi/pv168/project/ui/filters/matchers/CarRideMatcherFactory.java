package cz.muni.fi.pv168.project.ui.filters.matchers;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CarRideMatcherFactory {
    public EntityMatcher<CarRide> getDateMatcher(Date fromDate, Date toDate) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                LocalDateTime from = LocalDateTime.ofInstant(fromDate.toInstant(), ZoneId.systemDefault());
                LocalDateTime to = LocalDateTime.ofInstant(toDate.toInstant(), ZoneId.systemDefault());
                return from.isBefore(entity.getDate()) && entity.getDate().isBefore(to);
            }
        };
    }

    public EntityMatcher<CarRide> getDistanceMatcher(double fromDistance, double toDistance) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return fromDistance <= entity.getDistance() && entity.getDistance() <= toDistance;
            }
        };
    }

    public EntityMatcher<CarRide> getPassengersMatcher(int passengersCount) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return entity.getNumberOfPassengers() == passengersCount;
            }
        };
    }

    public EntityMatcher<CarRide> getNameMatcher(String name) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return entity.getTitle().contains(name);
            }
        };
    }


    public EntityMatcher<CarRide> getCategoryMatcher(Category category) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return category.equals(entity.getCategory());
            }
        };
    }

    public EntityMatcher<CarRide> getCurrencyMatcher(Currency currency) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return currency.equals(entity.getCurrency());
            }
        };
    }
}
