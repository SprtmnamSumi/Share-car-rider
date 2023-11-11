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


public final class CarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    private final CarRideMatcherFactory matcherFactory;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        matcherFactory = new CarRideMatcherFactory();
        rowSorter.setRowFilter(rideCompoundMatcher);
    }

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

    public void filterByDate(Date fromDate, Date toDate) {
        rideCompoundMatcher.addMatcher(DATE_FILTER, matcherFactory.getDateMatcher(fromDate, toDate));
    }

    public void filterByDistance(int fromDistance, int toDistance) {
        rideCompoundMatcher.addMatcher(DISTANCE_FILTER, matcherFactory.getDistanceMatcher(fromDistance, toDistance));
    }

    public void filterByPassengers(int passengersCount) {
        rideCompoundMatcher.addMatcher(PASSENGERS_FILTER, matcherFactory.getPassengersMatcher(passengersCount));
    }

    public void removeFilter(Filters filter) {
        rideCompoundMatcher.removeMatcher(filter);
    }

    public RideCompoundMatcher getRideCompoundMatcher() {
        return this.rideCompoundMatcher;
    }

    public static class RideCompoundMatcher extends EntityMatcher<CarRide> {

        private final TableRowSorter<CarRideTableModel> rowSorter;

        private final Map<Filters, EntityMatcher<CarRide>> entityMatchers = new HashMap<>();

        private RideCompoundMatcher(TableRowSorter<CarRideTableModel> rowSorter) {
            this.rowSorter = rowSorter;
        }

        private void addMatcher(Filters key, EntityMatcher<CarRide> matcher) {
            entityMatchers.put(key, matcher);
            rowSorter.sort();
        }

        private void removeMatcher(Filters key) {
            entityMatchers.remove(key);
            rowSorter.sort();
        }

        public List<CarRide> getData() {
            return rowSorter.getModel().getAll().stream()
                    .filter(carRide -> entityMatchers.values().stream()
                            .allMatch(match -> match.evaluate(carRide))).toList();
        }
        @Override
        public boolean evaluate(CarRide carRide) {
            return entityMatchers.values().stream().allMatch(m -> m.evaluate(carRide));
        }
    }
}
