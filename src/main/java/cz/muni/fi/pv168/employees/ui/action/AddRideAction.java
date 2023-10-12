package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.ui.dialog.RideDialog;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddRideAction extends AbstractAction {

    private final JTable carRidesTable;
    private final TestDataGenerator testDataGenerator;
    private final ListModel<Department> departmentListModel;

    public AddRideAction(JTable carRidesTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
        super("Add", Icons.ADD_ICON);
        this.carRidesTable = carRidesTable;
        this.testDataGenerator = testDataGenerator;
        this.departmentListModel = departmentListModel;
        putValue(SHORT_DESCRIPTION, "Adds new employee");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var employeeTableModel = (EmployeeTableModel) carRidesTable.getModel();
//        var dialog = new EmployeeDialog(testDataGenerator.createTestEmployee(), departmentListModel);
        var dialog = new RideDialog(testDataGenerator.createTestRide(), departmentListModel);
//        dialog.show(carRidesTable, "Add Car ride")
//                .ifPresent(employeeTableModel::addRow);
    }
}
