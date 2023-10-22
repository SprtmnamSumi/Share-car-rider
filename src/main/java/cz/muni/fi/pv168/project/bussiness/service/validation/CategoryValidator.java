package cz.muni.fi.pv168.project.bussiness.service.validation;

import cz.muni.fi.pv168.project.bussiness.model.Category;
import cz.muni.fi.pv168.project.bussiness.service.validation.common.StringLengthValidator;

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
