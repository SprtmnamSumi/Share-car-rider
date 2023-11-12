package cz.muni.fi.pv168.project.ui.validation;

public interface Validator<T> {
    boolean evaluate(T value);
}
