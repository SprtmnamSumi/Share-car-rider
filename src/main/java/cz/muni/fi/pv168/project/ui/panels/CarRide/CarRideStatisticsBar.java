package cz.muni.fi.pv168.project.ui.panels.CarRide;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;

public class CarRideStatisticsBar extends JPanel {
    private final TableModel model;
    private final JLabel filteredDistance = new JLabel();
    private final JLabel filteredExpenses = new JLabel();
    private final JLabel todayExpenses = new JLabel();
    private final JLabel totalDistance = new JLabel();
    private final JLabel totalExpenses = new JLabel();
    private final JLabel totalRevenues = new JLabel();
    private final JLabel totalRides = new JLabel();
    private final Border labelBorder = BorderFactory.createEmptyBorder(0, 0, 0, 5);

    public CarRideStatisticsBar(TableModel model) {
        super(new BorderLayout());
        this.model = model;

        JPanel filteredRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(filteredRow, BorderLayout.PAGE_START);
        filteredRow.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
        filteredDistance.setBorder(labelBorder);
        filteredRow.add(filteredDistance);
        filteredExpenses.setBorder(labelBorder);
        filteredRow.add(filteredExpenses);
        todayExpenses.setBorder(labelBorder);
        filteredRow.add(todayExpenses);

        JPanel totalRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(totalRow, BorderLayout.CENTER);
        totalRow.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0));
        totalDistance.setBorder(labelBorder);
        totalRow.add(totalDistance);
        totalRides.setBorder(labelBorder);
        totalRow.add(totalRides);
        totalRevenues.setBorder(labelBorder);
        totalRow.add(totalRevenues);
        totalExpenses.setBorder(labelBorder);
        totalRow.add(totalExpenses);

        updateFilteredStats();
        updateTotalStats();
    }

    public void updateFilteredStats() {
        filteredDistance.setText("Filtered distance: 50 km");
        filteredExpenses.setText("Filtered expenses: 50 km");
    }

    public void updateTotalStats() {
        totalDistance.setText("Total distance: 123 km");
        totalRides.setText("Total rides: " + model.getRowCount());
        totalRevenues.setText("Total revenues: 500 CZK");
        totalExpenses.setText("Total expenses: 400 CZK");
    }
}
