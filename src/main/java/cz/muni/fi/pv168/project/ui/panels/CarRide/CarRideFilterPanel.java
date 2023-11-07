package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.filters.DateFilterPanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;
import cz.muni.fi.pv168.project.ui.panels.filters.DistanceFilterPanel;
import cz.muni.fi.pv168.project.ui.panels.filters.PassengersFilterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CarRideFilterPanel extends JPanel {

    private final DistanceFilterPanel distanceFilter;
    private final DateFilterPanel dateFilter;
    private final PassengersFilterPanel passengerFilter;
    private final ComboBoxPanel categoryPanel;
    private final ComboBoxPanel currencyPanel;
    private final List<String> currencyModel = Arrays.stream(Currency.values()).map(Currency::name).toList();
    private final List<String> categories;

    public CarRideFilterPanel(CarRideTableFilter filter) {
        super(new FlowLayout(FlowLayout.LEFT));

        TestDataGenerator testDataGenerator = new TestDataGenerator();
        categories = testDataGenerator.createTestCategories(10).stream().map(Category::getName).toList();

        passengerFilter = new PassengersFilterPanel(filter);
        this.add(passengerFilter);

        dateFilter = new DateFilterPanel(filter);
        this.add(dateFilter);

        categoryPanel = new ComboBoxPanel("Category");
        categoryPanel.setComboBoxItems(categories);
        this.add(categoryPanel);

        currencyPanel = new ComboBoxPanel("Currency");
        currencyPanel.setComboBoxItems(currencyModel);
        this.add(currencyPanel);

        distanceFilter = new DistanceFilterPanel(filter);
        this.add(distanceFilter);

        JButton filterButton = new JButton("Reset Filter");
        this.add(filterButton);
        filterButton.addActionListener((a) -> resetFilters());
        resetFilters();
    }
    private void resetFilters(){
        dateFilter.reset();
        distanceFilter.reset();
        passengerFilter.reset();
        categoryPanel.getComboBox().setSelectedIndex(0);
        currencyPanel.getComboBox().setSelectedIndex(0);
    }
}
