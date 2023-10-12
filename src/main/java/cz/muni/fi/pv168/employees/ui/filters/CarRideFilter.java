package cz.muni.fi.pv168.employees.ui.filters;

import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.employees.ui.filters.matchers.EntityMatchers;
import cz.muni.fi.pv168.employees.ui.filters.matchers.employee.EmployeeGenderMatcher;
import cz.muni.fi.pv168.employees.ui.filters.values.SpecialFilterGenderValues;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.util.Either;

import javax.swing.table.TableRowSorter;
import java.util.stream.Stream;

/**
 * Class holding all filters for the EmployeeTable.
 */
public final class EmployeeTableFilter {
    private final EmployeeCompoundMatcher employeeCompoundMatcher;

    public EmployeeTableFilter(TableRowSorter<EmployeeTableModel> rowSorter) {
        employeeCompoundMatcher = new EmployeeCompoundMatcher(rowSorter);
        rowSorter.setRowFilter(employeeCompoundMatcher);
    }

    public void filterGender(Either<SpecialFilterGenderValues, Gender> selectedItem) {
        selectedItem.apply(
                l -> employeeCompoundMatcher.setGenderMatcher(l.getMatcher()),
                r -> employeeCompoundMatcher.setGenderMatcher(new EmployeeGenderMatcher(r))
        );
    }

    /**
     * Container class for all matchers for the EmployeeTable.
     *
     * This Matcher evaluates to true, if all contained {@link EntityMatcher} instances
     * evaluate to true.
     */
    private static class EmployeeCompoundMatcher extends EntityMatcher<Employee> {

        private final TableRowSorter<EmployeeTableModel> rowSorter;
        private EntityMatcher<Employee> genderMatcher = EntityMatchers.all();

        private EmployeeCompoundMatcher(TableRowSorter<EmployeeTableModel> rowSorter) {
            this.rowSorter = rowSorter;
        }

        private void setGenderMatcher(EntityMatcher<Employee> genderMatcher) {
            this.genderMatcher = genderMatcher;
            rowSorter.sort();
        }

        @Override
        public boolean evaluate(Employee employee) {
            return Stream.of(genderMatcher)
                    .allMatch(m -> m.evaluate(employee));
        }
    }
}
