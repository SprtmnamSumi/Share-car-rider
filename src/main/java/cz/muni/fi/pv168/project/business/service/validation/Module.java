package cz.muni.fi.pv168.project.business.service.validation;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.*;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Validator<CarRide>>() {
        }).to(CarRideValidator.class);
        bind(new TypeLiteral<Validator<Category>>() {
        }).to(CategoryValidator.class);
        bind(new TypeLiteral<Validator<Template>>() {
        }).to(CarRideTemplateValidator.class);
        bind(new TypeLiteral<Validator<Currency>>() {
        }).to(CurrencyValidator.class);
        bind(new TypeLiteral<Validator<CurrencyConversion>>() {
        }).to(CorversionValidator.class);
    }
}
