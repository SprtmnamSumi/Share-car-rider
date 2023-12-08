package cz.muni.fi.pv168.project.ui.filters;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.ui.filters.matchers.CarRideMatcherFactory;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.table.TableRowSorter;
import java.util.Date;


public final class CarRideTableFilter implements ICarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    private final CarRideMatcherFactory matcherFactory;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        matcherFactory = new CarRideMatcherFactory();
        rowSorter.setRowFilter(rideCompoundMatcher);
    }

    @Override
    public void filterByEntity(Model value, Filters filter) {
        switch (filter) {
            case CATEGORY_FILTER ->
                    rideCompoundMatcher.addMatcher(Filters.CATEGORY_FILTER, matcherFactory.getCategoryMatcher((Category) value));
            case CURRENCY_FILTER ->
                    rideCompoundMatcher.addMatcher(Filters.CURRENCY_FILTER, matcherFactory.getCurrencyMatcher((Currency) value));
            default -> {
            }
        }
    }

    @Override
    public void filterByDate(Date fromDate, Date toDate) {
        rideCompoundMatcher.addMatcher(Filters.DATE_FILTER, matcherFactory.getDateMatcher(fromDate, toDate));
    }

    @Override
    public void filterByDistance(int fromDistance, int toDistance) {
        rideCompoundMatcher.addMatcher(Filters.DISTANCE_FILTER, matcherFactory.getDistanceMatcher(fromDistance, toDistance));
    }

    @Override
    public void filterByPassengers(int passengersCount) {
        rideCompoundMatcher.addMatcher(Filters.PASSENGERS_FILTER, matcherFactory.getPassengersMatcher(passengersCount));
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
