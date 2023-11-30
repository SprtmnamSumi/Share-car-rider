package cz.muni.fi.pv168.project.storage.sql;


import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.storage.sql.dao.DataAccessObject;
import cz.muni.fi.pv168.project.storage.sql.dao.DataStorageException;
import cz.muni.fi.pv168.project.storage.sql.entity.Entity;
import cz.muni.fi.pv168.project.storage.sql.entity.mapper.EntityMapper;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link Repository} for {@link T} entity using SQL database.
 *
 * @author Vojtech Sassmann
 */
public class SqlRepository<T extends Model, K extends Entity> implements Repository<T> {

    private final DataAccessObject<K> EntityDao;
    private final EntityMapper<K, T> EntityMapper;

    public SqlRepository(
            DataAccessObject<K> EntityDao,
            EntityMapper<K, T> EntityMapper) {
        this.EntityDao = EntityDao;
        this.EntityMapper = EntityMapper;
    }

    @Override
    public List<T> findAll() {
        return EntityDao
                .findAll()
                .stream()
                .map(EntityMapper::mapToBusiness)
                .toList();
    }

    @Override
    public void create(T newEntity) {
        EntityDao.create(EntityMapper.mapNewEntityToDatabase(newEntity));
    }

    @Override
    public void update(T entity) {
        var existingCategory = EntityDao.findByGuid(entity.getGuid())
                .orElseThrow(() -> new DataStorageException("T not found, guid: " + entity.getGuid()));
        var updatedCategory = EntityMapper.mapExistingEntityToDatabase(entity, existingCategory.getId());

        EntityDao.update(updatedCategory);
    }

    @Override
    public void deleteByGuid(String guid) {
        EntityDao.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        EntityDao.deleteAll();
    }

    @Override
    public boolean existsByGuid(String guid) {
        return EntityDao.existsByGuid(guid);
    }

    @Override
    public Optional<T> findByGuid(String guid) {
        return EntityDao
                .findByGuid(guid)
                .map(EntityMapper::mapToBusiness);
    }
}
