package cz.muni.fi.pv168.project.business.service.crud;

import com.google.common.collect.Sets;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;

class CategoryCrudService extends ICrudServiceImpl<Category> {
    private final Validator<String> deleteValidator;
    private final Validator<Void> deleteAllValidator;

    @Inject
    CategoryCrudService(ICrudServiceImpl<CarRide> carRideCrudService,
                        ICrudServiceImpl<Template> templateCrudService,
                        Repository<Category> entityRepository,
                        Validator<Category> entityValidator,
                        GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
        this.deleteValidator = model ->
                carRideCrudService.findAll().stream().noneMatch(carRide -> carRide.getCategory().getGuid().matches(model))
                        && templateCrudService.findAll().stream().noneMatch(template -> template.getCategory().getGuid().matches(model))
                        ? ValidationResult.success()
                        : ValidationResult.failed("Category is referenced in another table");

        this.deleteAllValidator = x ->
                Sets.intersection(
                        Stream.concat(
                                carRideCrudService.findAll()
                                        .stream()
                                        .map(carRide -> carRide.getCategory().getGuid()),
                                templateCrudService.findAll()
                                        .stream()
                                        .map(template -> template.getCategory().getGuid())
                        ).collect(Collectors.toSet()),
                        super.findAll()
                                .stream()
                                .map(Model::getGuid)
                                .collect(Collectors.toSet())).isEmpty()
                        ? ValidationResult.success()
                        : ValidationResult.failed("Category is referenced in another table");
    }

    @Override
    public ValidationResult deleteByGuid(String guid) {
        ValidationResult result = deleteValidator.validate(guid);
        return result.isValid() ? super.deleteByGuid(guid) : result;
    }

    @Override
    public ValidationResult deleteAll() {
        ValidationResult result = deleteAllValidator.validate(null);
        return result.isValid() ? super.deleteAll() : result;
    }
}
