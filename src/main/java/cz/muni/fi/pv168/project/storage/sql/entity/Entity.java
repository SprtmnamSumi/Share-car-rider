package cz.muni.fi.pv168.project.storage.sql.entity;

public abstract class Entity {
    private final long id;

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}