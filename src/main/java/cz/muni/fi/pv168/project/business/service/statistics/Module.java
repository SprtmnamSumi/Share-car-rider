package cz.muni.fi.pv168.project.business.service.statistics;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(ICarRideStatistics.class).to(CarRideStatistics.class);
    }
}
