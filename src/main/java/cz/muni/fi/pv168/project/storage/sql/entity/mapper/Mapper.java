package cz.muni.fi.pv168.project.storage.sql.entity.mapper;

import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.storage.sql.entity.Entity;

public abstract class Mapper<T extends Entity, K extends Model> implements EntityMapper<T, K> {

    abstract T getEntity(K entity, Long dbID);

    @Override
    public T mapNewEntityToDatabase(K entity) {
        return getEntity(entity, null);
    }

    @Override
    public T mapExistingEntityToDatabase(K entity, Long dbId) {
        return getEntity(entity, dbId);
    }
}
