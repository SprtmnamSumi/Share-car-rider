package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DistanceFilterPanel extends FilterPanel {
    private final TextFieldPanel distanceFromPanel;

    private final TextFieldPanel distanceToPanel;
    private final CarRideTableFilter filter;

    private final KeyListener listener = new TypeListener();

    public DistanceFilterPanel(CarRideTableFilter filter) {
        super();
        this.filter = filter;

        distanceFromPanel = new TextFieldPanel("Distance from");
        distanceToPanel = new TextFieldPanel("Distance to");

        distanceFromPanel.getTextField().addKeyListener(listener);
        distanceToPanel.getTextField().addKeyListener(listener);

        this.add(distanceFromPanel);
        this.add(distanceToPanel);
    }

    @Override
    public void reset() {
        distanceFromPanel.getTextField().setText("");
        distanceToPanel.getTextField().setText("");
        listener.keyReleased(null);
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            int leftBound = isIntInputValid(distanceFromPanel.getTextField())
                    ? Integer.parseInt(distanceFromPanel.getTextField().getText())
                    : Integer.MIN_VALUE;

            int rightBound = isIntInputValid(distanceToPanel.getTextField())
                    ? Integer.parseInt(distanceToPanel.getTextField().getText())
                    : Integer.MAX_VALUE;

            filter.filterByDistance(leftBound, rightBound);
        }
    }
}
