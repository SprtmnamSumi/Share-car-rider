package cz.muni.fi.pv168.project.ui.panels.CarRide;


import cz.muni.fi.pv168.project.ui.panels.commonPanels.NameValuePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;

public class CarRideStatisticsPanel extends JPanel {
    private final TableModel model;
    private final NameValuePanel filteredDistance = new NameValuePanel("Filtered distance");
    private final NameValuePanel filteredExpenses = new NameValuePanel("Filtered expenses:");
    private final NameValuePanel todayExpenses = new NameValuePanel("Today expenses:");
    private final NameValuePanel totalDistance = new NameValuePanel("Total distance:");
    private final NameValuePanel totalExpenses = new NameValuePanel("Total expenses:");
    private final NameValuePanel totalRevenues = new NameValuePanel("Total revenues:");
    private final NameValuePanel totalRides = new NameValuePanel("Total rides:");

    public CarRideStatisticsPanel(TableModel model) {
        super(new BorderLayout());
        this.model = model;

        JPanel filteredRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(filteredRow, BorderLayout.PAGE_START);
        filteredRow.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
        filteredRow.add(filteredDistance);
        filteredRow.add(filteredExpenses);
        filteredRow.add(todayExpenses);

        JPanel totalRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(totalRow, BorderLayout.CENTER);
        totalRow.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0));
        totalRow.add(totalDistance);
        totalRow.add(totalRides);
        totalRow.add(totalRevenues);
        totalRow.add(totalExpenses);

        updateFilteredStats();
        updateTotalStats();
    }

    public void updateFilteredStats() {
        filteredDistance.setValue("50 km");
        filteredExpenses.setValue("50 km");
    }

    public void updateTotalStats() {
        totalDistance.setValue("123 km");
        totalRides.setValue(""+model.getRowCount());
        totalRevenues.setValue("500 CZK");
        totalExpenses.setValue("400 CZK");
        todayExpenses.setValue("100 CZK");
    }
}
