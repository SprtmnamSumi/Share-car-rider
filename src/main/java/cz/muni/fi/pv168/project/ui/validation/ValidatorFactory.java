package cz.muni.fi.pv168.project.ui.validation;

import java.util.Arrays;

public class ValidatorFactory {

    public static Validator<String> titleValidator() {
        return value -> value.length() >= 2;
    }

    public static Validator<String> intValidator() {
        return value -> ValidationUtils.validateInteger(value) && Integer.parseInt(value) >= 0;
    }

    public static Validator<String> doubleValidator() {
        return value -> ValidationUtils.validateDouble(value) && Double.parseDouble(value) >= 0.0f;
    }

    public static Validator<String> combinedValidator(Validator... valdiators) {
        return (t) -> Arrays.stream(valdiators).toList().parallelStream().allMatch(v -> v.evaluate(t));
    }

    public static Validator<String> eitherValidator(Validator... valdiators) {
        return (t) -> Arrays.stream(valdiators).toList().parallelStream().anyMatch(v -> v.evaluate(t));
    }
}
