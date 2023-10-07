package cz.muni.fi.pv168.project.gui.filterbar;

import cz.muni.fi.pv168.project.gui.filterbar.panels.TextFieldPanel;
import cz.muni.fi.pv168.project.gui.filterbar.panels.SpinnerDatePanel;
import cz.muni.fi.pv168.project.gui.filterbar.panels.ComboBoxPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class FilterBar extends JPanel{
    private final static Dimension DEFAULT_DIMENSION = new Dimension(50,50);
    private final static Border DEFAULT_BORDER = BorderFactory.createEmptyBorder(0, 25, 0, 25);

    private final TextFieldPanel numberOfPassengersPanel;
    private final SpinnerDatePanel dateFromPanel;
    private final SpinnerDatePanel dateToPanel;
    private final ComboBoxPanel categoryPanel;
    private final ComboBoxPanel currencyPanel;
    private final TextFieldPanel distanceFromPanel;
    private final TextFieldPanel distanceToPanel;

    public FilterBar() {
        super();

        // Set look
        this.setBorder(DEFAULT_BORDER);
        this.setPreferredSize(DEFAULT_DIMENSION);

        numberOfPassengersPanel = new TextFieldPanel("Number Of Passengers");
        this.add(numberOfPassengersPanel);

        dateFromPanel = new SpinnerDatePanel("Date From");
        this.add(dateFromPanel);

        dateToPanel = new SpinnerDatePanel("Date to");
        this.add(dateToPanel);

        categoryPanel = new ComboBoxPanel("Category");
        categoryPanel.setComboBoxItems(/* Example list */ List.of("Car", "Bus", "Train"));
        this.add(categoryPanel);

        currencyPanel = new ComboBoxPanel("Currency");
        currencyPanel.setComboBoxItems(/* Example list */ List.of("Euro", "Dollar", "Czech Crown"));
        this.add(currencyPanel);

        distanceFromPanel = new TextFieldPanel("Distance from");
        this.add(distanceFromPanel);

        distanceToPanel = new TextFieldPanel("Distance to");
        this.add(distanceToPanel);
    }
}