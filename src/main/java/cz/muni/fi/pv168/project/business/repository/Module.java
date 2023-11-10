package cz.muni.fi.pv168.project.business.repository;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.*;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.storage.InMemoryRepository;

import java.util.ArrayList;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        var testDataGenerator = new TestDataGenerator();
        bind(new TypeLiteral<Repository<Template>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<Template>()));
        bind(new TypeLiteral<Repository<CarRide>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<CarRide>()));
        bind(new TypeLiteral<Repository<Category>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<Category>()));
        bind(new TypeLiteral<Repository<Currency>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<Currency>()));

        bind(new TypeLiteral<Repository<CurrencyConversion>>() {
        }).toInstance(new InMemoryRepository<>(new ArrayList<CurrencyConversion>()));
    }
}
