package cz.muni.fi.pv168.project.business.service.crud;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<ICrudService<CarRide>>(){}).to(CarRideCrudService.class);
        bind(new TypeLiteral<ICrudService<Category>>(){}).to(CategoryCrudService.class);
        bind(new TypeLiteral<ICrudService<Template>>(){}).to(CarRideTemplateCrudService.class);
        bind(new TypeLiteral<ICrudService<Currency>>(){}).to(CurrencyCrudService.class);
    }
}
