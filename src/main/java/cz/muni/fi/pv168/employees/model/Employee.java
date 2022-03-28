package cz.muni.fi.pv168.employees.model;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private Gender gender;
    private Department department;

    public Employee(String firstName, String lastName, Gender gender, Department department) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = Objects.requireNonNull(gender, "gender must not be null");
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
