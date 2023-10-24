package cz.muni.fi.pv168.project.business.service.validation;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.validation.common.StringLengthValidator;

import java.util.List;

public class CarRideTemplateValidator implements Validator<Template> {
    @Override
    public ValidationResult validate(Template model) {
        var validators = List.of(
                Validator.extracting(Template::getTitle, new StringLengthValidator(2, 150, "Title"))
        );
        return Validator.compose(validators).validate(model);
    }
}
