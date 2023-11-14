package cz.muni.fi.pv168.project.business.service.validation;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;

import java.util.List;

class EntityValidatorFactory {

    static Validator<CarRide> getCarRideValidator() {
        return (carRide) -> {
            var validators = List.of(
                    Validator.extracting(CarRide::getTitle, ValidatorFactory.stringValidator(2, 150))
            );
            return Validator.compose(validators).validate(carRide);
        };
    }

    static Validator<Category> getCategoryValidator() {
        return (category) -> {
            var validators = List.of(
                    Validator.extracting(Category::getName, ValidatorFactory.stringValidator(2, 150))
            );
            return Validator.compose(validators).validate(category);
        };
    }

    static Validator<Template> getTemplateValidator() {
        return (template) -> {
            var validators = List.of(
                    Validator.extracting(Template::getTitle, ValidatorFactory.stringValidator(2, 150))
            );
            return Validator.compose(validators).validate(template);
        };
    }

    static Validator<Currency> getCurrencyValidator() {
        return (currency) -> {
            var validators = List.of(
                    Validator.extracting(Currency::getName, ValidatorFactory.stringValidator(2, 150))
            );
            return Validator.compose(validators).validate(currency);
        };
    }
}
