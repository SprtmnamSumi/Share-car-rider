package cz.muni.fi.pv168.project.business.service.validation;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.validation.common.StringLengthValidator;

import java.util.List;

public class CurrencyValidator implements Validator<Currency> {
    @Override
    public ValidationResult validate(Currency model) {

        var validators = List.of(
                Validator.extracting(Currency::getName, new StringLengthValidator(0, 150, "Title"))
        );
        return Validator.compose(validators).validate(model);
    }
}
