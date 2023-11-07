package cz.muni.fi.pv168.project.ui.filters.matchers;

import cz.muni.fi.pv168.project.business.model.CarRide;

public class DistanceIntervalMatcher extends EntityMatcher<CarRide>{

    private final int fromDistance;
    private final int toDistance;

    public DistanceIntervalMatcher(int fromDistance, int toDistance){
        this.fromDistance = fromDistance;
        this.toDistance = toDistance;
    }

    @Override
    public boolean evaluate(CarRide entity) {
        return fromDistance <= entity.getDistance() && entity.getDistance() <= toDistance;
    }
}
