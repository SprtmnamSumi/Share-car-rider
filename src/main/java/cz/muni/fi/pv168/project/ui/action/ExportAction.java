package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.gui.dialog.ImportDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public final class ExportAction extends AbstractAction {


    public ExportAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Category> departmentListModel) {
        super("Export", null);
        putValue(SHORT_DESCRIPTION, "Export");
//        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        var employeeTableModel = (EmployeeTableModel) employeeTable.getModel();
//        var dialog = new EmployeeDialog(testDataGenerator.createTestEmployee(), departmentListModel);
//        dialog.show(employeeTable, "Add Employee")
//                .ifPresent(employeeTableModel::addRow);
        ImportDialog popupDialog = new ImportDialog(new JFrame("Popup"), "str");
        popupDialog.setSize(300, 200);

        // Center the custom dialog on the screen
        popupDialog.setLocationRelativeTo(null);

        // Make the custom dialog visible
        popupDialog.setVisible(true);
    }
}
