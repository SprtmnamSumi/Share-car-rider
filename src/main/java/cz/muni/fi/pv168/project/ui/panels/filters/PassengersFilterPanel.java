package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.filters.Filters;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PassengersFilterPanel extends FilterPanel {

    private final TextFieldPanel passengersField;
    private final CarRideTableFilter filter;

    private final KeyListener listener = new TypeListener();

    public PassengersFilterPanel(CarRideTableFilter filter) {
        super();
        this.filter = filter;
        passengersField = new TextFieldPanel("Number Of Passengers");
        passengersField.getTextField().addKeyListener(listener);
        this.add(passengersField);
    }
    @Override
    public void reset() {
        passengersField.getTextField().setText("");
        listener.keyReleased(null);
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (isIntInputValid(passengersField.getTextField())) {
                filter.filterByPassengers(Integer.parseInt(passengersField.getTextField().getText()));
            } else {
                filter.removeFilter(Filters.PASSENGERS_FILTER);
            }
        }
    }
}
