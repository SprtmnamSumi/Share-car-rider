package cz.muni.fi.pv168.project.storage.sql.entity;

public class CategoryEntity extends Entity {
    private String guid;
    private String name;
    private int colour;

    // Constructors
    public CategoryEntity(Long id, String guid, String name, int colour) {
        super(id);
        this.guid = guid;
        this.name = name;
        this.colour = colour;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}