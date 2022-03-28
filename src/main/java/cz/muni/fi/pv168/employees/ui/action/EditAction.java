package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class EditAction extends AbstractAction {

    private final JTable employeeTable;
    private final ListModel<Department> departmentListModel;

    public EditAction(JTable employeeTable, ListModel<Department> departmentListModel) {
        super("Edit", Icons.EDIT_ICON);
        this.employeeTable = employeeTable;
        this.departmentListModel = departmentListModel;
        putValue(SHORT_DESCRIPTION, "Edits selected employee");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = employeeTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        var employeeTableModel = (EmployeeTableModel) employeeTable.getModel();
        int modelRow = employeeTable.convertRowIndexToModel(selectedRows[0]);
        var employee = employeeTableModel.getEntity(modelRow);
        var dialog = new EmployeeDialog(employee, departmentListModel);
        dialog.show(employeeTable, "Edit Employee")
                .ifPresent(employeeTableModel::updateRow);
    }
}
