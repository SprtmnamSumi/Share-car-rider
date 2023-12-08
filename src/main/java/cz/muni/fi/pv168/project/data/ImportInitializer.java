package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.GuidProvider;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import java.util.List;

public class ImportInitializer {
    private final TableModel<Category> categories;
    private final TableModel<CarRide> rides;
    private final TableModel<Currency> currencies;
    private final TableModel<Template> templates;
    TestDataGenerator generator;

    public ImportInitializer(GuidProvider guidProvider, TableModel<Category> categories, TableModel<CarRide> rides, TableModel<Currency> currencies, TableModel<Template> templates) {
        this.categories = categories;
        this.rides = rides;
        this.currencies = currencies;
        this.templates = templates;
        generator = new TestDataGenerator(guidProvider);
    }

    public void initializeeCarRide(List<CarRide> ride) {
        ride.forEach(rides::addRow);
    }

    public void initializeCategory(List<Category> cat) {
        cat.forEach(categories::addRow);
    }

    public void initializeCurrency(List<Currency> cur) {
        cur.forEach(currencies::addRow);
    }

    public void initializeTemplate(List<Template> template) {
        template.forEach(templates::addRow);
    }

    public void redoCarRide() {
        while (rides.getRowCount() > 0) {
            rides.deleteRow(0);
        }
    }

    public void redoCategory() {
        while (categories.getRowCount() > 0) {
            categories.deleteRow(0);
        }
    }

    public void redoCurrency() {
        while (currencies.getRowCount() > 0) {
            currencies.deleteRow(0);
        }
    }

    public void redoTemplate() {
        while (templates.getRowCount() > 0) {
            templates.deleteRow(0);
        }
    }
}
