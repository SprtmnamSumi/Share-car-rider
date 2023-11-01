package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideListModel;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ComboBoxPanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.SpinnerDatePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CarRideFilterPanel extends JPanel {
    private final List<String> currencyModel = Arrays.stream(Currency.values()).map(Currency::name).toList();
    private final List<String> categories;

    public CarRideFilterPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        TestDataGenerator testDataGenerator = new TestDataGenerator();
        categories = testDataGenerator.createTestCategories(10).stream().map(Category::getName).toList();


        TextFieldPanel numberOfPassengersPanel = new TextFieldPanel("Number Of Passengers");
        this.add(numberOfPassengersPanel);

        SpinnerDatePanel dateFromPanel = new SpinnerDatePanel("Date from");
        this.add(dateFromPanel);

        SpinnerDatePanel dateToPanel = new SpinnerDatePanel("Date to");
        this.add(dateToPanel);

        ComboBoxPanel categoryPanel = new ComboBoxPanel("Category");
        categoryPanel.setComboBoxItems(categories);
        this.add(categoryPanel);

        ComboBoxPanel currencyPanel = new ComboBoxPanel("Currency");
        currencyPanel.setComboBoxItems(currencyModel);
        this.add(currencyPanel);

        TextFieldPanel distanceFromPanel = new TextFieldPanel("Distance from");
        this.add(distanceFromPanel);

        TextFieldPanel distanceToPanel = new TextFieldPanel("Distance to");
        this.add(distanceToPanel);

        JButton filterButton = new JButton("Reset Filter");
        this.add(filterButton);
        filterButton.addActionListener((a) ->
                {
                    numberOfPassengersPanel.getTextField().setText("");
                    distanceFromPanel.getTextField().setText("");
                    distanceToPanel.getTextField().setText("");
                    categoryPanel.getComboBox().setSelectedIndex(0);
                    currencyPanel.getComboBox().setSelectedIndex(0);
                }
        );
    }
}
