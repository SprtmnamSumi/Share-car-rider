package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.filters.Filters;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ValidatedTextFieldPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NameFilterPanel extends FilterPanel {
    private final ValidatedTextFieldPanel nameField = new ValidatedTextFieldPanel("Name Of CarRide", ValidatorFactory.stringValidator(0, 256));
    private final ICarRideTableFilter filter;
    private final KeyListener listener = new TypeListener();

    public NameFilterPanel(ICarRideTableFilter filter) {
        super();
        this.filter = filter;
        nameField.getTextField().addKeyListener(listener);
        this.add(nameField);
    }

    @Override
    public void reset() {
        nameField.clear();
        listener.keyReleased(null);
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (nameField.evaluate()) {
                filter.filterByName(nameField.getTextField().getText());
            } else {
                filter.removeFilter(Filters.NAME_FILTER);
            }
        }
    }
}
