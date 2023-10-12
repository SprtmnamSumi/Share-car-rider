package cz.muni.fi.pv168.employees.ui.panels;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilterBar extends JPanel {
    private final cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel numberOfPassengersPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.SpinnerDatePanel dateFromPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.SpinnerDatePanel dateToPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.ComboBoxPanel categoryPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.ComboBoxPanel currencyPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel distanceFromPanel;
    private final cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel distanceToPanel;

    public FilterBar() {
        super(new FlowLayout(FlowLayout.LEFT));

//        // Set look
//        this.setMaximumSize(dimension);

        numberOfPassengersPanel = new cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel("Number Of Passengers");
        this.add(numberOfPassengersPanel);

        dateFromPanel = new cz.muni.fi.pv168.employees.ui.panels.SpinnerDatePanel("Date From");
        this.add(dateFromPanel);

        dateToPanel = new cz.muni.fi.pv168.employees.ui.panels.SpinnerDatePanel("Date to");
        this.add(dateToPanel);

        categoryPanel = new cz.muni.fi.pv168.employees.ui.panels.ComboBoxPanel("Category");
        categoryPanel.setComboBoxItems(/* Example list */ List.of("Car", "Bus", "Train"));
        this.add(categoryPanel);

        currencyPanel = new cz.muni.fi.pv168.employees.ui.panels.ComboBoxPanel("Currency");
        currencyPanel.setComboBoxItems(/* Example list */ List.of("Euro", "Dollar", "Czech Crown"));
        this.add(currencyPanel);

        distanceFromPanel = new cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel("Distance from");
        this.add(distanceFromPanel);

        distanceToPanel = new cz.muni.fi.pv168.employees.ui.panels.TextFieldPanel("Distance to");
        this.add(distanceToPanel);
    }
}
