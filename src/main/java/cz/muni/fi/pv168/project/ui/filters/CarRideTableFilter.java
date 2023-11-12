package cz.muni.fi.pv168.project.ui.filters;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Entity;
import cz.muni.fi.pv168.project.ui.filters.matchers.CarRideMatcherFactory;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cz.muni.fi.pv168.project.ui.filters.Filters.*;


public final class CarRideTableFilter implements ICarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    private final CarRideMatcherFactory matcherFactory;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        matcherFactory = new CarRideMatcherFactory();
        rowSorter.setRowFilter(rideCompoundMatcher);
    }

    @Override
    public void filterByEntity(Entity value, Filters filter) {
        switch (filter) {
            case CATEGORY_FILTER ->
                    rideCompoundMatcher.addMatcher(CATEGORY_FILTER, matcherFactory.getCategoryMatcher((Category) value));
            case CURRENCY_FILTER ->
                    rideCompoundMatcher.addMatcher(CURRENCY_FILTER, matcherFactory.getCurrencyMatcher((Currency) value));
            default -> {
            }
        }
    }

    @Override
    public void filterByDate(Date fromDate, Date toDate) {
        rideCompoundMatcher.addMatcher(DATE_FILTER, matcherFactory.getDateMatcher(fromDate, toDate));
    }

    @Override
    public void filterByDistance(int fromDistance, int toDistance) {
        rideCompoundMatcher.addMatcher(DISTANCE_FILTER, matcherFactory.getDistanceMatcher(fromDistance, toDistance));
    }

    @Override
    public void filterByPassengers(int passengersCount) {
        rideCompoundMatcher.addMatcher(PASSENGERS_FILTER, matcherFactory.getPassengersMatcher(passengersCount));
    }

    @Override
    public void removeFilter(Filters filter) {
        rideCompoundMatcher.removeMatcher(filter);
    }

    @Override
    public RideCompoundMatcher getRideCompoundMatcher() {
        return this.rideCompoundMatcher;
    }

}
