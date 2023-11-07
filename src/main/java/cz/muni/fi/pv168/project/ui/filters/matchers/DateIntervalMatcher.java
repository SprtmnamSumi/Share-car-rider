package cz.muni.fi.pv168.project.ui.filters.matchers;

import cz.muni.fi.pv168.project.business.model.CarRide;

import java.time.ZoneOffset;
import java.util.Date;

public class DateIntervalMatcher extends EntityMatcher<CarRide> {

    private final Date fromDate;
    private final Date toDate;

    public DateIntervalMatcher(Date fromDate, Date toDate){
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public boolean evaluate(CarRide entity) {
        long entityTime = entity.getDate().toInstant(ZoneOffset.ofHours(0)).toEpochMilli();
        return fromDate.toInstant().toEpochMilli() <= entityTime && entityTime <= toDate.toInstant().toEpochMilli();
    }
}
