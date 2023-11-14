package cz.muni.fi.pv168.project.business.service.validation;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Validator<CarRide>>(){}).toInstance(EntityValidatorFactory.getCarRideValidator());
        bind(new TypeLiteral<Validator<Category>>(){}).toInstance(EntityValidatorFactory.getCategoryValidator());
        bind(new TypeLiteral<Validator<Template>>(){}).toInstance(EntityValidatorFactory.getTemplateValidator());
        bind(new TypeLiteral<Validator<Currency>>(){}).toInstance(EntityValidatorFactory.getCurrencyValidator());
    }
}
