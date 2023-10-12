package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.old.Department;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class InfoAction extends AbstractAction {


    public InfoAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
        super("Info", null);
        putValue(SHORT_DESCRIPTION, "Info");
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

    public static final class currenciesAction extends AbstractAction {


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
}
