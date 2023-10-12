package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class currenciesAction extends AbstractAction {



    public currenciesAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
        super("Currencies", null);
        putValue(SHORT_DESCRIPTION, "Currencies");
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
