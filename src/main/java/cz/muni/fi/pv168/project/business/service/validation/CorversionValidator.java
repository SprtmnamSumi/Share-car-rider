package cz.muni.fi.pv168.project.business.service.validation;

import cz.muni.fi.pv168.project.business.model.CurrencyConversion;
import cz.muni.fi.pv168.project.business.service.validation.common.StringLengthValidator;

import java.util.List;

public class CorversionValidator implements Validator<CurrencyConversion> {
    @Override
    public ValidationResult validate(CurrencyConversion model) {

        var validators = List.of(
                Validator.extracting(CurrencyConversion::getGuid, new StringLengthValidator(0, 150, "Title"))
        );
        return Validator.compose(validators).validate(model);
    }
}
