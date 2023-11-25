package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;

public interface EntityProvider {
    CarRide getCarRide();

    Category getCategory();

    Template getTemplate();

    Currency getCurrency();
}
