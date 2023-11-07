package cz.muni.fi.pv168.project.ui.panels.filters;

import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TextFieldPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DistanceFilterPanel extends JPanel {
    private final TextFieldPanel distanceFromPanel;

    private final TextFieldPanel distanceToPanel;
    private final CarRideTableFilter filter;

    public DistanceFilterPanel(CarRideTableFilter filter) {
        super();
        this.filter = filter;

        distanceFromPanel = new TextFieldPanel("Distance from");
        distanceToPanel = new TextFieldPanel("Distance to");
        KeyListener listener = new TypeListener();

        distanceFromPanel.getTextField().addKeyListener(listener);
        distanceToPanel.getTextField().addKeyListener(listener);

        this.add(distanceFromPanel);
        this.add(distanceToPanel);
    }

    public void reset(){
        distanceFromPanel.getTextField().setText("");
        distanceToPanel.getTextField().setText("");
    }

    private class TypeListener extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e){
            int leftBound = distanceFromPanel.getTextField().getText().isEmpty()
                    ? Integer.MIN_VALUE
                    : Integer.parseInt(distanceFromPanel.getTextField().getText());

            int rightBound = distanceToPanel.getTextField().getText().isEmpty()
                    ? Integer.MAX_VALUE
                    : Integer.parseInt(distanceToPanel.getTextField().getText());

            filter.filterByDistance(leftBound, rightBound);
        }
    }
}
