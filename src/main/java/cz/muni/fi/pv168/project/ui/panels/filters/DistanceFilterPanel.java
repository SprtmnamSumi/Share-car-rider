package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.ValidatedTextFieldPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DistanceFilterPanel extends FilterPanel {
    private final ValidatedTextFieldPanel distanceFromPanel = new ValidatedTextFieldPanel("Distance from");
    private final ValidatedTextFieldPanel distanceToPanel = new ValidatedTextFieldPanel("Distance to");
    private final CarRideTableFilter filter;

    private final KeyListener listener = new TypeListener();

    public DistanceFilterPanel(CarRideTableFilter filter) {
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
            int leftBound = distanceFromPanel.evaluate()
                    ? Integer.parseInt(distanceFromPanel.getTextField().getText())
                    : Integer.MIN_VALUE;

            int rightBound = distanceToPanel.evaluate()
                    ? Integer.parseInt(distanceToPanel.getTextField().getText())
                    : Integer.MAX_VALUE;

            filter.filterByDistance(leftBound, rightBound);
        }
    }
}
