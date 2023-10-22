package cz.muni.fi.pv168.project.bussiness.service.validation;

import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.bussiness.service.validation.common.StringLengthValidator;

import java.util.List;

public class CarRideValidator implements Validator<CarRide> {
    @Override
    public ValidationResult validate(CarRide model) {
        var validators = List.of(
                Validator.extracting(CarRide::getTitle, new StringLengthValidator(2, 150, "Title"))
        );
        return Validator.compose(validators).validate(model);
    }
}
