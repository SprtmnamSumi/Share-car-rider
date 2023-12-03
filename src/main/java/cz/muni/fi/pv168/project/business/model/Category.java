package cz.muni.fi.pv168.project.business.model;

import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;

import java.util.Objects;

public class Category extends Entity {

    private String name;
    private int colour;

    public Category(String guid, String name, int colour) {
        super(guid);
        setName(name);
        setColour(colour);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = Objects.requireNonNull(colour, "colour must not be null");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;

        return name.equals(category.name) && colour == category.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, colour);
    }
}
