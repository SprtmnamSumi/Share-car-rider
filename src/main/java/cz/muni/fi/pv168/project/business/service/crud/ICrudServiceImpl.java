package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.Entity;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import java.util.List;

public class ICrudServiceImpl<T extends Entity> implements ICrudService<T> {
    private final Repository<T> entityRepository;
    private final Validator<T> entityValidator;
    private final GuidProvider guidProvider;

    public ICrudServiceImpl(Repository<T> entityRepository,
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
        var validationResult = entityValidator.validate(newEntity);
        if (newEntity.getGuid() == null || newEntity.getGuid().isBlank()) {
            newEntity.setGuid(guidProvider.newGuid());
        } else if (entityRepository.existsByGuid(newEntity.getGuid())) {
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
            entityRepository.update(entity);
        }

        return validationResult;
    }

    @Override
    public void deleteByGuid(String guid) {
        entityRepository.deleteByGuid(guid);
    }

    @Override
    public void deleteAll() {
        entityRepository.deleteAll();
    }
}
