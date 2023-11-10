package cz.muni.fi.pv168.project.business.service.crud;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(ICarRideICrudService.class).to(CarRideCrudService.class);
        bind(ICarRideTempalteService.class).to(CarRideTemplateCrudService.class);
        bind(ICategoryCrudService.class).to(CategoryCrudService.class);
        bind(ICurrencyCrudService.class).to(CurrencyCrudService.class);
        bind(IConversionCrudService.class).to(ConversionCrudService.class);
    }
}
