package cz.muni.fi.pv168.employees.ui.filters.values;

import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.ui.filters.matchers.EntityMatcher;
import cz.muni.fi.pv168.employees.ui.filters.matchers.EntityMatchers;

import java.util.Objects;

public enum SpecialFilterGenderValues {
    BOTH(EntityMatchers.all());

    private final EntityMatcher<Employee> matcher;

    SpecialFilterGenderValues(EntityMatcher<Employee> matcher) {
        this.matcher = Objects.requireNonNull(matcher, "matcher cannot be null");
    }

    public EntityMatcher<Employee> getMatcher() {
        return matcher;
    }
}
