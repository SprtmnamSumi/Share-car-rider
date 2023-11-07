package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

public class PassengersFilterPanel extends JPanel {

    private final TextFieldPanel passengersField;
    private final CarRideTableFilter filter;

    public PassengersFilterPanel(CarRideTableFilter filter) {
        super();
        this.filter = filter;

        passengersField = new TextFieldPanel("Number Of Passengers");
        KeyListener listener = new TypeListener();

        passengersField.getTextField().addKeyListener(listener);

        this.add(passengersField);
    }

    public void reset() {
        passengersField.getTextField().setText("");
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (isFilterValid()) {
                filter.filterByPassengers(Integer.parseInt(passengersField.getTextField().getText()));
            } else {
                filter.removePassengersFilter();
            }
        }
    }

    private boolean isFilterValid() {
        try {
            int value = Integer.parseInt(passengersField.getTextField().getText());
            return value >= 0;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }
}
