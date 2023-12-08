package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import java.util.List;
import javax.inject.Inject;
import org.tinylog.Logger;

class ICrudServiceImpl<T extends Model> implements ICrudService<T> {
    private final Repository<T> entityRepository;
    private final Validator<T> entityValidator;
    private final GuidProvider guidProvider;

    @Inject
    ICrudServiceImpl(Repository<T> entityRepository,
                     Validator<T> entityValidator,
                     GuidProvider guidProvider) {
        this.entityRepository = entityRepository;
        this.entityValidator = entityValidator;
        this.guidProvider = guidProvider;
    }

    @Override
    public List<T> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public ValidationResult create(T newEntity) {
        Logger.info("Creating new entity: " + newEntity);
        var validationResult = entityValidator.validate(newEntity);
        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(guidProvider.newGuid());
        } else if (entityRepository.existsByGuid(newEntity.getGuid())) {
            Logger.error(newEntity.getClass().getName() + " with given guid already exists: ");
            throw new EntityAlreadyExistsException(newEntity.getClass().getName() + " with given guid already exists: " + newEntity.getGuid());
        }
        if (validationResult.isValid()) {
            entityRepository.create(newEntity);
        }

        return validationResult;
    }

    @Override
    public ValidationResult update(T entity) {
        var validationResult = entityValidator.validate(entity);
        if (validationResult.isValid()) {
            Logger.info("Entity updated successfully" + entity);
            entityRepository.update(entity);
        }

        return validationResult;
    }

    @Override
    public ValidationResult deleteByGuid(String guid) {
        entityRepository.deleteByGuid(guid);
        Logger.info("Entity deleted successfully with given guid " + guid);
        return ValidationResult.success();
    }

    @Override
    public void deleteAll() {
        Logger.info("All entities deleted successfully");
        entityRepository.deleteAll();
    }
}
