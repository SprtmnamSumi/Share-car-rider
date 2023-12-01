package cz.muni.fi.pv168.project.storage.sql.entity;

public abstract class Entity {
    private final Long id;

    public Entity(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}