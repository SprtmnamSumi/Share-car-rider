package cz.muni.fi.pv168.project.ui.filters.matchers.OLDemployee;

import cz.muni.fi.pv168.project.entities.old.Department;
import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;

import java.util.Collection;
import java.util.List;

public class EmployeeDepartmentMatcher extends EntityMatcher<Employee> {

    private final Collection<Department> selectedDepartments;

    public EmployeeDepartmentMatcher(Department selectedDepartment) {
        this.selectedDepartments = List.of(selectedDepartment);
    }

    public EmployeeDepartmentMatcher(Collection<Department> selectedDepartments) {
        this.selectedDepartments = selectedDepartments;
    }

    @Override
    public boolean evaluate(Employee employee) {
        return selectedDepartments.stream()
                .anyMatch(d -> employee.getDepartment().equals(d));
    }
}
