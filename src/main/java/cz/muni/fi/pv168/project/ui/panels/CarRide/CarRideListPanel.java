package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.ui.renderers.CarRideRender;

import javax.swing.*;
import java.awt.*;


public class CarRideListPanel extends JPanel {

    public CarRideListPanel(ListModel<CarRide> carRideListModel) {
        var list = new JList<>(carRideListModel);
        list.setCellRenderer(new CarRideRender());
        setLayout(new BorderLayout());
        add(new JScrollPane(list));
    }
}
