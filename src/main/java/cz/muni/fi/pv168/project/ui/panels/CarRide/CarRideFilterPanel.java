package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.filters.*;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarRideFilterPanel extends JPanel {
    private final FilterPanel distanceFilter;
    private final FilterPanel dateFilter;
    private final FilterPanel passengerFilter;
    private final CategoryFilterPanel categoryPanel;
    //private final FilterPanel currencyPanel;

    public CarRideFilterPanel(CarRideTableFilter filter, CategoryTableModel categories, CurrencyTableModel currencyTableModel) {
        super(new FlowLayout(FlowLayout.LEFT));

        passengerFilter = new PassengersFilterPanel(filter);
        this.add(passengerFilter);

        dateFilter = new DateFilterPanel(filter);
        this.add(dateFilter);

        categoryPanel = new CategoryFilterPanel(filter, categories);
        this.add(categoryPanel);

        //currencyPanel = new CurrencyFilterPanel(filter, currencyTableModel);
        //this.add(currencyPanel);

        distanceFilter = new DistanceFilterPanel(filter);
        this.add(distanceFilter);

        JButton filterButton = new JButton("Reset Filter");
        filterButton.addActionListener((a) -> resetFilters());
        this.add(filterButton);

        updateValues();
        resetFilters();
    }

    public void updateValues() {
        categoryPanel.updateValues();
    }
    private void resetFilters(){
        dateFilter.reset();
        distanceFilter.reset();
        passengerFilter.reset();
        categoryPanel.reset();
        //currencyPanel.getComboBox().setSelectedItem(null);
    }
}
