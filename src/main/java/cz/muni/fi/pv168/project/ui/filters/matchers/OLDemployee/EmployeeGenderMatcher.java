package cz.muni.fi.pv168.project.ui.filters.matchers.OLDemployee;

import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.entities.old.Gender;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;

public class EmployeeGenderMatcher extends EntityMatcher<Employee> {
    private final Gender gender;

    public EmployeeGenderMatcher(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean evaluate(Employee employee) {
        return employee.getGender() == gender;
    }
}
