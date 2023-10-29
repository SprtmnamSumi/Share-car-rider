package cz.muni.fi.pv168.project.business.repository;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.storage.InMemoryRepository;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        var testDataGenerator = new TestDataGenerator();
        bind(new TypeLiteral<Repository<Template>>(){}).toInstance(new InMemoryRepository<>(testDataGenerator.createTestTemplates(10)));
        bind(new TypeLiteral<Repository<CarRide>>(){}).toInstance(new InMemoryRepository<>(testDataGenerator.createTestRides(10)));
        bind(new TypeLiteral<Repository<Category>>(){}).toInstance(new InMemoryRepository<>(testDataGenerator.createTestCategories(10)));
    }

}
