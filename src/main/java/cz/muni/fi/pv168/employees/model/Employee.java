package cz.muni.fi.pv168.employees.model;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private Department department;

    public Employee(String firstName, String lastName, Department department) {
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = Objects.requireNonNull(department, "department must not be null");
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }
}
