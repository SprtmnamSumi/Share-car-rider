//package cz.muni.fi.pv168.project.ui.action.old;
//
//import cz.muni.fi.pv168.project.data.TestDataGenerator;
//import cz.muni.fi.pv168.project.entities.old.Department;
//import cz.muni.fi.pv168.project.ui.dialog.OLDEmployeeDialog;
//import cz.muni.fi.pv168.project.ui.model.OLDEmployeeTableModel;
//import cz.muni.fi.pv168.project.ui.resources.Icons;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//
//public final class AddAction extends AbstractAction {
//
//    private final JTable employeeTable;
//    private final TestDataGenerator testDataGenerator;
//    private final ListModel<Department> departmentListModel;
//
//    public AddAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
//        super("Add", Icons.ADD_ICON);
//        this.employeeTable = employeeTable;
//        this.testDataGenerator = testDataGenerator;
//        this.departmentListModel = departmentListModel;
//        putValue(SHORT_DESCRIPTION, "Adds new employee");
//        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        var employeeTableModel = (OLDEmployeeTableModel) employeeTable.getModel();
//        var dialog = new OLDEmployeeDialog(testDataGenerator.createTestEmployee(), departmentListModel);
//        dialog.show(employeeTable, "Add Employee")
//                .ifPresent(employeeTableModel::addRow);
//    }
//}
