package cz.muni.fi.pv168.employees.ui.filters.matchers.employee;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.ui.filters.matchers.EntityMatcher;

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