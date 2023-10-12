package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Category;
import cz.muni.fi.pv168.employees.ui.dialog.RideDialog;
import cz.muni.fi.pv168.employees.ui.model.CarRideTableModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddRideAction extends AbstractAction {

    private final JTable carRidesTable;
    private final TestDataGenerator testDataGenerator;
    private final ListModel<Category> categoriestListModel;

    public AddRideAction(JTable carRidesTable, TestDataGenerator testDataGenerator, ListModel<Category> categoriestListModel) {
        super("Add", Icons.ADD_ICON);
        this.carRidesTable = carRidesTable;
        this.testDataGenerator = testDataGenerator;
        this.categoriestListModel = categoriestListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var carRidesTableModel = (CarRideTableModel) carRidesTable.getModel();
        var dialog = new RideDialog(testDataGenerator.createTestRide(), categoriestListModel);
        dialog.show(carRidesTable, "Add Car ride")
                .ifPresent(carRidesTableModel::addRow);
    }
}
