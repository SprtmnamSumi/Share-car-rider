package cz.muni.fi.pv168.project.ui.filters.matchers.OLDemployee;

import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;

public class EmployeeDepartmentNoNerdMatcher extends EntityMatcher<Employee> {

    @Override
    public boolean evaluate(Employee employee) {
        return !"IT".equals(employee.getDepartment().getName());
    }
}
