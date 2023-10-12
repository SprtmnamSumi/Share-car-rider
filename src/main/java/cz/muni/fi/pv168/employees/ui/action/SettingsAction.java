package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class SettingsAction extends AbstractAction {



    public SettingsAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
        super("Settings", null);
        putValue(SHORT_DESCRIPTION, "Settings");
//        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        var employeeTableModel = (EmployeeTableModel) employeeTable.getModel();
//        var dialog = new EmployeeDialog(testDataGenerator.createTestEmployee(), departmentListModel);
//        dialog.show(employeeTable, "Add Employee")
//                .ifPresent(employeeTableModel::addRow);
    }
}
