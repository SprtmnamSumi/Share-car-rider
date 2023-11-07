package cz.muni.fi.pv168.project.ui.filters.matchers;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;

import java.time.ZoneOffset;
import java.util.Date;

public class CarRideMatcherFactory {
    public EntityMatcher<CarRide> getDateMatcher(Date fromDate, Date toDate) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                long entityTime = entity.getDate().toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
                return fromDate.toInstant().toEpochMilli() <= entityTime && entityTime <= toDate.toInstant().toEpochMilli();
            }
        };
    }

    public EntityMatcher<CarRide> getDistanceMatcher(int fromDistance, int toDistance) {
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

    public EntityMatcher<CarRide> getCategoryMatcher(Category category) {
        return new EntityMatcher<>() {
            @Override
            public boolean evaluate(CarRide entity) {
                return category.equals(entity.getCategory());
            }
        };
    }
}
