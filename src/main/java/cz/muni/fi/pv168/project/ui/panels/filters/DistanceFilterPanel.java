package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.filters.ICarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ValidatedTextFieldPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DistanceFilterPanel extends FilterPanel {
    private final ValidatedTextFieldPanel distanceFromPanel = new ValidatedTextFieldPanel("Distance from", ValidatorFactory.doubleValidator());
    private final ValidatedTextFieldPanel distanceToPanel = new ValidatedTextFieldPanel("Distance to", ValidatorFactory.doubleValidator());
    private final ICarRideTableFilter filter;

    private final KeyListener listener = new TypeListener();

    public DistanceFilterPanel(ICarRideTableFilter filter) {
        super();
        this.filter = filter;

        distanceFromPanel.getTextField().addKeyListener(listener);
        distanceToPanel.getTextField().addKeyListener(listener);

        this.add(distanceFromPanel);
        this.add(distanceToPanel);
    }

    @Override
    public void reset() {
        distanceFromPanel.clear();
        distanceToPanel.clear();
        listener.keyReleased(null);
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            double leftBound = distanceFromPanel.evaluate()
                    ? Double.parseDouble(distanceFromPanel.getTextField().getText())
                    : Integer.MIN_VALUE;

            double rightBound = distanceToPanel.evaluate()
                    ? Double.parseDouble(distanceToPanel.getTextField().getText())
                    : Integer.MAX_VALUE;

            filter.filterByDistance(leftBound, rightBound);
        }
    }
}
