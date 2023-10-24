package cz.muni.fi.pv168.project.business.model;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(GuidProvider.class).to(UuidGuidProvider.class);
    }
}
