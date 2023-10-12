package cz.muni.fi.pv168.employees.ui.panels;

import cz.muni.fi.pv168.employees.model.CarRide;
import cz.muni.fi.pv168.employees.ui.renderers.CarRideRender;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with employee records in a list.
 */
public class CarRideListPanel extends JPanel {

    public CarRideListPanel(ListModel<CarRide> carRideListModel) {
        var list = new JList<>(carRideListModel);
        list.setCellRenderer(new CarRideRender());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
