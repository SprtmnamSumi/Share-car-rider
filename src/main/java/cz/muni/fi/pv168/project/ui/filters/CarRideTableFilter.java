package cz.muni.fi.pv168.project.ui.filters;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.ui.filters.matchers.*;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.util.stream.Stream;


public final class CarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        rowSorter.setRowFilter(rideCompoundMatcher);
    }

    public void filterByTime(Date fromDate, Date toDate) {
        rideCompoundMatcher.setDateMatcher(new DateIntervalMatcher(fromDate, toDate));
    }

    public void filterByDistance(int fromDistance, int toDistance) {
        rideCompoundMatcher.setDistanceMatcher(new DistanceIntervalMatcher(fromDistance, toDistance));
    }

    public void filterByPassengers(int passengers) {
        rideCompoundMatcher.setPassengerMatcher(new PassengersCountMatcher(passengers));
    }

    public void removePassengersFilter() {
        rideCompoundMatcher.setPassengerMatcher(EntityMatchers.all());
    }
    public void removeDistanceFilter() {
        rideCompoundMatcher.setDistanceMatcher(EntityMatchers.all());
    }

    public void removeDateFilter() {
        rideCompoundMatcher.setDateMatcher(EntityMatchers.all());
    }


    private static class RideCompoundMatcher extends EntityMatcher<CarRide> {

        private final TableRowSorter<CarRideTableModel> rowSorter;
        private EntityMatcher<CarRide> dateMatcher = EntityMatchers.all();
        private EntityMatcher<CarRide> passengerMatcher = EntityMatchers.all();
        private EntityMatcher<CarRide> distanceMatcher = EntityMatchers.all();

        private RideCompoundMatcher(TableRowSorter<CarRideTableModel> rowSorter) {
            this.rowSorter = rowSorter;
        }

        private void setDistanceMatcher(EntityMatcher<CarRide> distanceMatcher) {
            this.distanceMatcher = distanceMatcher;
            rowSorter.sort();
        }

        private void setPassengerMatcher(EntityMatcher<CarRide> passengerMatcher) {
            this.passengerMatcher = passengerMatcher;
            rowSorter.sort();
        }

        private void setDateMatcher(EntityMatcher<CarRide> dateMatcher) {
            this.dateMatcher = dateMatcher;
            rowSorter.sort();
        }


        @Override
        public boolean evaluate(CarRide carRide) {
            return Stream.of(dateMatcher, distanceMatcher, passengerMatcher).allMatch(m -> m.evaluate(carRide));
        }
    }
}
