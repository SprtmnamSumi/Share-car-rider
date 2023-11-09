package cz.muni.fi.pv168.project.business.service.validation;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.crud.*;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<Validator<CarRide>>(){}).to(CarRideValidator.class);
        bind(new TypeLiteral<Validator<Category>>(){}).to(CategoryValidator.class);
        bind(new TypeLiteral<Validator<Template>>(){}).to(CarRideTemplateValidator.class);
        bind(new TypeLiteral<Validator<Currency>>(){}).to(CurrencyValidator.class);
    }
}
