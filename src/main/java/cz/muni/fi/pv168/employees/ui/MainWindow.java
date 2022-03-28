package cz.muni.fi.pv168.employees.ui;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.ui.action.AddAction;
import cz.muni.fi.pv168.employees.ui.action.DeleteAction;
import cz.muni.fi.pv168.employees.ui.action.QuitAction;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.employees.ui.model.DepartmentListModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainWindow {

    private final JFrame frame;
    private final JTable employeeTable;
    private final TestDataGenerator testDataGenerator = new TestDataGenerator();
    private final ListModel<Department> departmentListModel = new DepartmentListModel(testDataGenerator.getDepartments());

    private final Action quitAction = new QuitAction();
    private final Action addAction;
    private final Action deleteAction;

    public MainWindow() {
        frame = createFrame();
        employeeTable = createEmployeeTable(testDataGenerator.createTestEmployees(10));
        addAction = new AddAction(employeeTable, testDataGenerator, departmentListModel);
        deleteAction = new DeleteAction(employeeTable);
        employeeTable.setComponentPopupMenu(createEmployeeTablePopupMenu());
        frame.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        frame.add(createToolbar(), BorderLayout.BEFORE_FIRST_LINE);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
    }

    public void show() {
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        var frame = new JFrame("Employee records");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private JTable createEmployeeTable(List<Employee> employees) {
        var model = new EmployeeTableModel(employees);
        var table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        return table;
    }

    private JPopupMenu createEmployeeTablePopupMenu() {
        var menu = new JPopupMenu();

        menu.add(deleteAction);

        var editMenuItem = new JMenuItem("Edit", Icons.EDIT_ICON);
        editMenuItem.addActionListener(this::editSelectedRow);
        editMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        editMenuItem.setToolTipText("Edits selected employee");
        editMenuItem.setMnemonic('e');
        menu.add(editMenuItem);
        return menu;
    }

    private JMenuBar createMenuBar() {
        var menuBar = new JMenuBar();
        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic('e');
        editMenu.add(addAction);
        editMenu.addSeparator();
        editMenu.add(quitAction);
        menuBar.add(editMenu);
        return menuBar;
    }

    private JToolBar createToolbar() {
        var toolbar = new JToolBar();
        toolbar.add(quitAction);
        toolbar.addSeparator();
        return toolbar;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        // here you can put the code for handling selection change
    }

    private void editSelectedRow(ActionEvent e) {
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
