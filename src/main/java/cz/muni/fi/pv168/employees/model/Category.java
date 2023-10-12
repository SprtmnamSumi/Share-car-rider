package cz.muni.fi.pv168.employees.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CarRide {

    private String title;
    private String description;
    private Gender distance;
    private LocalDate birthDate;
    private Department fuelConsumption;
    private int costOfFuelPerLitre;
    private int numberOfPassengers;
    private double comission;
    private LocalDateTime date;
    private Category category;

    public CarRide(String firstName, String lastName, Gender gender, LocalDate birthDate, Department department) {
        setTitle(firstName);
        setDescription(lastName);
        setDistance(gender);
        setBirthDate(birthDate);
        setFuelConsumption(department);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "firstName must not be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "lastName must not be null");
    }

    public Gender getDistance() {
        return distance;
    }

    public void setDistance(Gender distance) {
        this.distance = Objects.requireNonNull(distance, "gender must not be null");
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
    }

    public Department getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Department fuelConsumption) {
        this.fuelConsumption = Objects.requireNonNull(fuelConsumption, "department must not be null");
    }

    @Override
    public String toString() {
        return title + ' ' + description;
    }
}
