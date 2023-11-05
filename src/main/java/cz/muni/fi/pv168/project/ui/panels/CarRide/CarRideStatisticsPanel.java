package cz.muni.fi.pv168.project.ui.panels.CarRide;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.service.statistics.CarRideStatistics;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.NameValuePanel;

import javax.swing.*;
import java.awt.*;

public class CarRideStatisticsPanel extends JPanel {
    private final TableModel<CarRide> model;
    private final NameValuePanel filteredDistance = new NameValuePanel("Filtered distance:");
    private final NameValuePanel filteredExpenses = new NameValuePanel("Filtered expenses:");
    private final NameValuePanel todayExpenses = new NameValuePanel("Today expenses:");
    private final NameValuePanel totalDistance = new NameValuePanel("Total distance:");
    private final NameValuePanel totalExpenses = new NameValuePanel("Total expenses:");
    private final NameValuePanel totalRevenues = new NameValuePanel("Total revenues:");
    private final NameValuePanel totalRides = new NameValuePanel("Total rides:");

    public CarRideStatisticsPanel(TableModel<CarRide> model) {
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
        var entities = model.getAllEntities();
        filteredDistance.setValue(CarRideStatistics.getTotalDistance(entities).toString()); //Set to only filtered CarRides after filtering is finished.
        filteredExpenses.setValue(CarRideStatistics.getTotalExpenses(entities).toString()); //Set to only filtered CarRides after filtering is finished.
    }

    public void updateTotalStats() {
        var entities = model.getAllEntities();
        totalDistance.setValue(CarRideStatistics.getTotalDistance(entities).toString());
        totalRides.setValue(CarRideStatistics.getTotalRides(entities).toString());
        totalRevenues.setValue(CarRideStatistics.getTotalRevenues(entities).toString());
        totalExpenses.setValue(CarRideStatistics.getTotalExpenses(entities).toString());
        todayExpenses.setValue(CarRideStatistics.getTodayExpenses(entities).toString());
    }
}
