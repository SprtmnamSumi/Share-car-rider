package cz.muni.fi.pv168.project.ui.filters;

import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatchers;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.table.TableRowSorter;
import java.util.stream.Stream;


public final class CarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        rowSorter.setRowFilter(rideCompoundMatcher);
    }


    private static class RideCompoundMatcher extends EntityMatcher<CarRide> {

        private final TableRowSorter<CarRideTableModel> rowSorter;
        private final EntityMatcher<CarRide> genderMatcher = EntityMatchers.all();

        private RideCompoundMatcher(TableRowSorter<CarRideTableModel> rowSorter) {
            this.rowSorter = rowSorter;
        }

        @Override
        public boolean evaluate(CarRide carRide) {
            return Stream.of(genderMatcher)
                    .allMatch(m -> m.evaluate(carRide));
        }
    }
}
