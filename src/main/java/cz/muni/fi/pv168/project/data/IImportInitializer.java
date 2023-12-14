package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;

import java.util.List;

public interface IImportInitializer {
    void initializeCarRide(List<CarRide> rides, MODE mode);

    void initializeCategory(List<Category> categories, MODE mode);

    void initializeCurrency(List<Currency> currencies, MODE mode);

    void initializeTemplate(List<Template> templates, MODE mode);

    enum MODE{
        ADD, OVERWRITE, INTERSECTION
    }
}
