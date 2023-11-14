package cz.muni.fi.pv168.project.business.service.validation.common;

import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.Validator;

import java.util.Arrays;

public class ValidatorFactory {

    public static Validator<String> stringValidator(int min, int max) {
        return value -> min <= value.length() && value.length() <= max
                ? ValidationResult.success() : ValidationResult.failed(
                        String.format("String %s length is not within bounds %d - %d", value, min, max));
    }

    public static Validator<String> intValidator() {
        return value -> ValidationUtils.validateInteger(value) && Integer.parseInt(value) >= 0
                ? ValidationResult.success() : ValidationResult.failed(
                    String.format("Number %s is not Integer or is not positive", value));
    }

    public static Validator<String> doubleValidator() {
        return value -> ValidationUtils.validateDouble(value) && Double.parseDouble(value) >= 0.0f
                ? ValidationResult.success() : ValidationResult.failed(
                        String.format("Number %s is not Double or is not positive", value));
    }

    @SafeVarargs
    public static Validator<String> eitherValidator(Validator<String>... validators) {
        return (t) -> Arrays.stream(validators).toList().parallelStream().anyMatch(v -> v.validate(t).isValid())
                ? ValidationResult.success() : ValidationResult.failed("Not all are valid");
    }
}
