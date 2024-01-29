package cz.muni.fi.pv168.project.business.service.mocks;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Model;
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
    public void initializeCarRide(List<CarRide> rides, MODE mode) {
        initialize(carRideList, rides, mode);
    }


    @Override
    public void initializeCategory(List<Category> categories, MODE mode) {
        initialize(categoryList, categories, mode);
    }


    @Override
    public void initializeCurrency(List<Currency> currencies, MODE mode) {
        initialize(currencyList, currencies, mode);
    }


    @Override
    public void initializeTemplate(List<Template> templates, MODE mode) {
        initialize(templateList, templates, mode);
    }

    private <T extends Model> void initialize(List<T> list, List<T> newEntities, MODE mode) {
        switch (mode) {
            case ADD -> list.addAll(newEntities);
            case OVERWRITE -> {
                list.clear();
                list.addAll(newEntities);
            }
            case INTERSECTION -> {
            }
        }
    }

}
