package cz.muni.fi.pv168.project.business.model;

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
}
