package cz.muni.fi.pv168.project.business.service.crud;

import com.google.common.collect.Sets;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.repository.Repository;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;

class CurrencyCrudService extends ICrudServiceImpl<Currency> {
    private final Validator<String> deleteValidator;
    private final Validator<Void> deleteAllValidator;

    @Inject
    CurrencyCrudService(ICrudServiceImpl<CarRide> carRideCrudService,
                        ICrudServiceImpl<Template> templateCrudService,
                        Repository<Currency> entityRepository,
                        Validator<Currency> entityValidator,
                        GuidProvider guidProvider) {
        super(entityRepository, entityValidator, guidProvider);
        this.deleteValidator = model ->
                carRideCrudService.findAll().stream().noneMatch(carRide -> carRide.getCurrency().getGuid().matches(model))
                        && templateCrudService.findAll().stream().noneMatch(template -> template.getCurrency().getGuid().matches(model))
                        ? ValidationResult.success()
                        : ValidationResult.failed("Currency is referenced in another table");
        this.deleteAllValidator = x ->
                Sets.intersection(
                        Stream.concat(
                                carRideCrudService.findAll()
                                        .stream()
                                        .map(carRide -> carRide.getCurrency().getGuid()),
                                templateCrudService.findAll()
                                        .stream()
                                        .map(template -> template.getCurrency().getGuid())
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
        return result.getValidationErrors().isEmpty() ? super.deleteByGuid(guid) : result;
    }

    @Override
    public ValidationResult deleteAll() {
        ValidationResult result = deleteAllValidator.validate(null);
        return result.getValidationErrors().isEmpty() ? super.deleteAll() : result;
    }
}
