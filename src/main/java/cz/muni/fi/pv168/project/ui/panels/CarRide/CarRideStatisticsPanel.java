package cz.muni.fi.pv168.project.ui.panels.CarRide;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.service.statistics.ICarRideStatistics;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.NameValuePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarRideStatisticsPanel extends JPanel {
    private final TableModel<CarRide> model;
    private final NameValuePanel filteredDistance = new NameValuePanel("Filtered distance:");
    private final NameValuePanel filteredExpenses = new NameValuePanel("Filtered expenses:");
    private final NameValuePanel todayExpenses = new NameValuePanel("Today expenses:");
    private final NameValuePanel totalDistance = new NameValuePanel("Total distance:");
    private final NameValuePanel totalExpenses = new NameValuePanel("Total expenses:");
    private final NameValuePanel totalRevenues = new NameValuePanel("Total revenues:");
    private final NameValuePanel totalRides = new NameValuePanel("Total rides:");
    private final ICarRideStatistics ICarRideStatistics;
    private final CarRideTableFilter carRideTableFilter;

    public CarRideStatisticsPanel(TableModel<CarRide> model, CarRideTableFilter carRideTableFilter, ICarRideStatistics ICarRideStatistics1) {
        super(new BorderLayout());
        this.model = model;
        this.ICarRideStatistics = ICarRideStatistics1;
        this.carRideTableFilter = carRideTableFilter;

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
        var entities = carRideTableFilter.getRideCompoundMatcher().getData();
        for (CarRide carRide : entities) {
            System.out.println(carRide);
        }
        System.out.println("---END---\n\n\n\n\n\n\n\n");
        filteredDistance.setValue(String.format("%.2f", ICarRideStatistics.getTotalDistance(entities))); //Set to only filtered CarRides after filtering is finished.
        filteredExpenses.setValue(String.format("%.2f", ICarRideStatistics.getTotalExpenses(entities))); //Set to only filtered CarRides after filtering is finished.
    }

    public void updateTotalStats() {
        var entities = model.getAllEntities();
        totalDistance.setValue(String.format("%.2f", ICarRideStatistics.getTotalDistance(entities)));
        totalRides.setValue(ICarRideStatistics.getTotalRides(entities).toString());
        totalRevenues.setValue(String.format("%.2f", ICarRideStatistics.getTotalRevenues(entities)));
        totalExpenses.setValue(String.format("%.2f", ICarRideStatistics.getTotalExpenses(entities)));
        todayExpenses.setValue(String.format("%.2f", ICarRideStatistics.getTodayExpenses(entities)));
    }
}
