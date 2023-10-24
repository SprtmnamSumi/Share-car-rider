package cz.muni.fi.pv168.project.business.service.validation;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.validation.common.StringLengthValidator;

import java.util.List;

public class CategoryValidator implements Validator<Category> {
    @Override
    public ValidationResult validate(Category model) {

        var validators = List.of(
                Validator.extracting(Category::getName, new StringLengthValidator(2, 150, "Title"))
        );
        return Validator.compose(validators).validate(model);
    }
}
