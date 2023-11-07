package cz.muni.fi.pv168.project.ui.filters.matchers;

import cz.muni.fi.pv168.project.business.model.CarRide;

public class PassengersCountMatcher extends EntityMatcher<CarRide>{

    private final int passengersCount;

    public PassengersCountMatcher(int passengersCount){
        this.passengersCount = passengersCount;
    }

    @Override
    public boolean evaluate(CarRide entity) {
        return entity.getNumberOfPassengers() == passengersCount;
    }
}
