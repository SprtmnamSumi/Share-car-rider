package cz.muni.fi.pv168.project.data;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(ImportInitializer.class);
        bind(EntityProvider.class).to(PrefilledEntityProvider.class);
        bind(Initializer.class).to(DataInitializer.class);
    }
}
