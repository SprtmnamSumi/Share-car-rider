package cz.muni.fi.pv168.project.ui.filters;

import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatchers;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.table.TableRowSorter;
import java.util.stream.Stream;

/**
 * Class holding all filters for the EmployeeTable.
 */
public final class CarRideTableFilter {
    private final RideCompoundMatcher rideCompoundMatcher;

    public CarRideTableFilter(TableRowSorter<CarRideTableModel> rowSorter) {
        rideCompoundMatcher = new RideCompoundMatcher(rowSorter);
        rowSorter.setRowFilter(rideCompoundMatcher);
    }

//    public void filterGender(Either<SpecialFilterGenderValues, Gender> selectedItem) {
//        selectedItem.apply(
//                l -> rideCompoundMatcher.setGenderMatcher(l.getMatcher()),
//                r -> rideCompoundMatcher.setGenderMatcher(new EmployeeGenderMatcher(r))
//        );
//    }

    /**
     * Container class for all matchers for the EmployeeTable.
     * <p>
     * This Matcher evaluates to true, if all contained {@link EntityMatcher} instances
     * evaluate to true.
     */
    private static class RideCompoundMatcher extends EntityMatcher<CarRide> {

        private final TableRowSorter<CarRideTableModel> rowSorter;
        private final EntityMatcher<CarRide> genderMatcher = EntityMatchers.all();

        private RideCompoundMatcher(TableRowSorter<CarRideTableModel> rowSorter) {
            this.rowSorter = rowSorter;
        }

//        private void setGenderMatcher(EntityMatcher<Employee> genderMatcher) {
//            this.genderMatcher = genderMatcher;
//            rowSorter.sort();
//        }

        @Override
        public boolean evaluate(CarRide carRide) {
            return Stream.of(genderMatcher)
                    .allMatch(m -> m.evaluate(carRide));
        }
    }
}
