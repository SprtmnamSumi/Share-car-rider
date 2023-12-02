package cz.muni.fi.pv168.project.storage.sql.entity;

import cz.muni.fi.pv168.project.business.model.Model;

public abstract class Entity extends Model {
    private final Long id;

    public Entity(Long id, String guid) {
        super(guid);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}