package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
class DataInitializer implements Initializer {
    final TestDataGenerator generator;
    private final TableModel<Category> categories;
    private final TableModel<CarRide> rides;
    private final TableModel<Currency> currencies;
    private final TableModel<Template> templates;

    @Inject
    DataInitializer(GuidProvider guidProvider, TableModel<Category> categories, TableModel<CarRide> rides, TableModel<Currency> currencies, TableModel<Template> templates) {
        this.categories = categories;
        this.rides = rides;
        this.currencies = currencies;
        this.templates = templates;

        generator = new TestDataGenerator(guidProvider);
    }

    @Override
    public void initialize(int count) {
        List<Category> cat = generator.createTestCategories(count);
        cat.forEach(categories::addRow);

        var cur = generator.createTestCurrencie(count);
        cur.forEach(currencies::addRow);

        var template = generator.createTestTemplates(count, cat, cur);
        template.forEach(templates::addRow);

        var ride = generator.createTestRides(count, cat, cur);
        ride.forEach(rides::addRow);
    }
}
