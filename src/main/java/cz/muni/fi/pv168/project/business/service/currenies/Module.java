package cz.muni.fi.pv168.project.business.service.currenies;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(ICurrencyConverter.class).to(CurrencyConverter.class);
    }
}
