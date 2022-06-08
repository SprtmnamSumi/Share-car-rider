package cz.muni.fi.pv168.employees.ui;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.action.AddAction;
import cz.muni.fi.pv168.employees.ui.action.DeleteAction;
import cz.muni.fi.pv168.employees.ui.action.EditAction;
import cz.muni.fi.pv168.employees.ui.action.QuitAction;
import cz.muni.fi.pv168.employees.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.model.DepartmentListModel;

import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import java.awt.BorderLayout;
import java.util.List;

public class MainWindow {

    private final JFrame frame;

    private final Action quitAction = new QuitAction();
    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;

    public MainWindow() {
        frame = createFrame();
        var testDataGenerator = new TestDataGenerator();
        var departmentListModel = new DepartmentListModel(testDataGenerator.getDepartments());
        var employeeTable = createEmployeeTable(testDataGenerator.createTestEmployees(10), departmentListModel);
        addAction = new AddAction(employeeTable, testDataGenerator, departmentListModel);
        deleteAction = new DeleteAction(employeeTable);
        editAction = new EditAction(employeeTable, departmentListModel);
        employeeTable.setComponentPopupMenu(createEmployeeTablePopupMenu());
        frame.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        frame.add(createToolbar(), BorderLayout.BEFORE_FIRST_LINE);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        changeActionsState(0);
    }

    public void show() {
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        var frame = new JFrame("Employee records");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private JTable createEmployeeTable(List<Employee> employees, ListModel<Department> departmentListModel) {
        var model = new EmployeeTableModel(employees);
        var table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        var genderComboBox = new JComboBox<>(Gender.values());
        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
        var departmentComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(departmentListModel));
        table.setDefaultEditor(Department.class, new DefaultCellEditor(departmentComboBox));
        return table;
    }

    private JPopupMenu createEmployeeTablePopupMenu() {
        var menu = new JPopupMenu();
        menu.add(deleteAction);
        menu.add(editAction);
        menu.add(addAction);
        return menu;
    }

    private JMenuBar createMenuBar() {
        var menuBar = new JMenuBar();
        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic('e');
        editMenu.add(addAction);
        editMenu.add(editAction);
        editMenu.add(deleteAction);
        editMenu.addSeparator();
        editMenu.add(quitAction);
        menuBar.add(editMenu);
        return menuBar;
    }

    private JToolBar createToolbar() {
        var toolbar = new JToolBar();
        toolbar.add(quitAction);
        toolbar.addSeparator();
        toolbar.add(addAction);
        toolbar.add(editAction);
        toolbar.add(deleteAction);
        return toolbar;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        var count = selectionModel.getSelectedItemsCount();
        changeActionsState(count);
    }

    private void changeActionsState(int selectedItemsCount) {
        editAction.setEnabled(selectedItemsCount == 1);
        deleteAction.setEnabled(selectedItemsCount >= 1);
    }
}
