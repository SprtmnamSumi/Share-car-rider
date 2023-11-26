package cz.muni.fi.pv168.project.business.service.crud;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import javax.inject.Inject;

class CategoryCrudService extends ICrudServiceImpl<Category>{
    private final Validator<String> deleteValidator;
    @Inject
    CategoryCrudService(ICrudServiceImpl<CarRide> carRideCrudService, Repository<Category> entityRepository, Validator<Category> entityValidator, GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
        this.deleteValidator = model -> carRideCrudService.findAll().stream().noneMatch(carRide -> carRide.getCategory().getGuid().matches(model))
                ? ValidationResult.success()
                : ValidationResult.failed("Category is referenced in another table");
    }

    @Override
    public ValidationResult deleteByGuid(String guid){
        ValidationResult result = deleteValidator.validate(guid);
        return result.getValidationErrors().isEmpty() ? super.deleteByGuid(guid) : result;
    }
}
