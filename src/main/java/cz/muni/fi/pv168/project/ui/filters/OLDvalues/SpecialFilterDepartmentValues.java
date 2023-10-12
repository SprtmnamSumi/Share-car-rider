package cz.muni.fi.pv168.project.ui.filters.OLDvalues;

import cz.muni.fi.pv168.project.entities.old.Employee;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.project.ui.filters.matchers.EntityMatchers;
import cz.muni.fi.pv168.project.ui.filters.matchers.OLDemployee.EmployeeDepartmentNoNerdMatcher;

import java.util.Objects;

public enum SpecialFilterDepartmentValues {
    ALL(EntityMatchers.all()),
    NO_NERD(new EmployeeDepartmentNoNerdMatcher());

    private final EntityMatcher<Employee> matcher;

    SpecialFilterDepartmentValues(EntityMatcher<Employee> matcher) {
        this.matcher = Objects.requireNonNull(matcher, "matcher cannot be null");
    }

    public EntityMatcher<Employee> getMatcher() {
        return matcher;
    }
}
