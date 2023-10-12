package cz.muni.fi.pv168.employees.model;

import java.util.Objects;

public class Category {

    private String name;
    private String colour;


    public Category(String name, String colour) {
        setName(name);
        setColour(colour);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "firstName must not be null");
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = Objects.requireNonNull(colour, "lastName must not be null");
    }


    @Override
    public String toString() {
        return name + ' ' + colour;
    }
}
