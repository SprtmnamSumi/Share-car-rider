package cz.muni.fi.pv168.project.business.service.mocks;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.IImportInitializer;
import java.util.LinkedList;
import java.util.List;

public class MockImportInitializer implements IImportInitializer {

    public final List<CarRide> carRideList = new LinkedList<>();
    public final List<Category> categoryList = new LinkedList<>();
    public final List<Currency> currencyList = new LinkedList<>();
    public final List<Template> templateList = new LinkedList<>();

    @Override
    public void initializeCarRide(List<CarRide> rides, boolean rewrite) {
        if (rewrite) {
            carRideList.clear();
        }
        carRideList.addAll(rides);
    }


    @Override
    public void initializeCategory(List<Category> categories, boolean rewrite) {
        if (rewrite) {
            categoryList.clear();
        }
        categoryList.addAll(categories);
    }


    @Override
    public void initializeCurrency(List<Currency> currencies, boolean rewrite) {
        if (rewrite) {
            currencyList.clear();
        }
        currencyList.addAll(currencies);
    }


    @Override
    public void initializeTemplate(List<Template> templates, boolean rewrite) {
        if (rewrite) {
            templateList.clear();
        }
        templateList.addAll(templates);
    }

}
