package cz.muni.fi.pv168.project.ui.filters.matchers.OLDemployee;

import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;

import java.util.Collection;

public class EmployeeDepartmentCompoundMatcher extends EntityMatcher<Employee> {

    private final Collection<EntityMatcher<Employee>> employeeMatchers;

    public EmployeeDepartmentCompoundMatcher(Collection<EntityMatcher<Employee>> employeeMatchers) {
        this.employeeMatchers = employeeMatchers;
    }

    @Override
    public boolean evaluate(Employee employee) {
        return employeeMatchers.stream()
                .anyMatch(matcher -> matcher.evaluate(employee));
    }
}
