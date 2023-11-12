package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.filters.Filters;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.panels.filters.*;

import javax.swing.*;
import java.awt.*;

public class CarRideFilterPanel extends JPanel {
    private final FilterPanel distanceFilter;
    private final FilterPanel dateFilter;
    private final FilterPanel passengerFilter;
    private final FilterBoxPanel<Category> categoryPanel;
    private final FilterBoxPanel<Currency> currencyPanel;

    public CarRideFilterPanel(ICarRideTableFilter filter, CategoryTableModel categories, CurrencyTableModel currencyTableModel) {
        super(new FlowLayout(FlowLayout.LEFT));

        passengerFilter = new PassengersFilterPanel(filter);
        dateFilter = new DateFilterPanel(filter);
        categoryPanel = new FilterBoxPanel<>(filter, categories, Filters.CATEGORY_FILTER, "Category");
        currencyPanel = new FilterBoxPanel<>(filter, currencyTableModel, Filters.CURRENCY_FILTER, "Currency");
        distanceFilter = new DistanceFilterPanel(filter);

        this.add(passengerFilter);
        this.add(dateFilter);
        this.add(categoryPanel);
        this.add(currencyPanel);
        this.add(distanceFilter);

        JButton filterButton = new JButton("Reset Filter");
        filterButton.addActionListener((a) -> resetFilters());
        this.add(filterButton);

        updateValues();
        resetFilters();
    }

    public void updateValues() {
        categoryPanel.updateValues();
        currencyPanel.updateValues();
    }

    private void resetFilters() {
        dateFilter.reset();
        distanceFilter.reset();
        passengerFilter.reset();
        categoryPanel.reset();
        currencyPanel.reset();
    }
}
