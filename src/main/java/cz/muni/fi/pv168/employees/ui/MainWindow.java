package cz.muni.fi.pv168.employees.ui;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.action.AddAction;
import cz.muni.fi.pv168.employees.ui.action.DeleteAction;
import cz.muni.fi.pv168.employees.ui.action.EditAction;
import cz.muni.fi.pv168.employees.ui.action.QuitAction;
import cz.muni.fi.pv168.employees.ui.filters.EmployeeTableFilter;
import cz.muni.fi.pv168.employees.ui.filters.components.FilterComboboxBuilder;
import cz.muni.fi.pv168.employees.ui.filters.components.FilterListModelBuilder;
import cz.muni.fi.pv168.employees.ui.filters.values.SpecialFilterDepartmentValues;
import cz.muni.fi.pv168.employees.ui.filters.values.SpecialFilterGenderValues;
import cz.muni.fi.pv168.employees.ui.model.DepartmentListModel;
import cz.muni.fi.pv168.employees.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.employees.ui.model.EntityListModelAdapter;
import cz.muni.fi.pv168.employees.ui.panels.EmployeeListPanel;
import cz.muni.fi.pv168.employees.ui.panels.EmployeeTablePanel;
import cz.muni.fi.pv168.employees.ui.renderers.DepartmentRenderer;
import cz.muni.fi.pv168.employees.ui.renderers.GenderRenderer;
import cz.muni.fi.pv168.employees.ui.renderers.SpecialFilterDepartmentValuesRenderer;
import cz.muni.fi.pv168.employees.ui.renderers.SpecialFilterGenderValuesRenderer;
import cz.muni.fi.pv168.employees.util.Either;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Component;

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
        var employeeTableModel = new EmployeeTableModel(testDataGenerator.createTestEmployees(10));
        var employeeListModel = new EntityListModelAdapter<>(employeeTableModel);
        var employeeTablePanel = new EmployeeTablePanel(employeeTableModel, departmentListModel, this::changeActionsState);
        var employeeListPanel = new EmployeeListPanel(employeeListModel);

        addAction = new AddAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        deleteAction = new DeleteAction(employeeTablePanel.getTable());
        editAction = new EditAction(employeeTablePanel.getTable(), departmentListModel);
        employeeTablePanel.setComponentPopupMenu(createEmployeeTablePopupMenu());

        var tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees (table)", employeeTablePanel);
        tabbedPane.addTab("Employees (list)", employeeListPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        var rowSorter = new TableRowSorter<>(employeeTableModel);
        var employeeTableFilter = new EmployeeTableFilter(rowSorter);
        employeeTablePanel.getTable().setRowSorter(rowSorter);

        var genderFilter = createGenderFilter(employeeTableFilter);
        var departmentFilter = new JScrollPane(createDepartmentFilter(employeeTableFilter, departmentListModel));

        frame.add(createToolbar(genderFilter, departmentFilter), BorderLayout.BEFORE_FIRST_LINE);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        changeActionsState(0);
    }

    private static JList<Either<SpecialFilterDepartmentValues, Department>> createDepartmentFilter(
            EmployeeTableFilter employeeTableFilter, DepartmentListModel departmentListModel) {
        return FilterListModelBuilder.create(SpecialFilterDepartmentValues.class, departmentListModel)
                .setSelectedIndex(0)
                .setVisibleRowsCount(3)
                .setSpecialValuesRenderer(new SpecialFilterDepartmentValuesRenderer())
                .setValuesRenderer(new DepartmentRenderer())
                .setFilter(employeeTableFilter::filterDepartment)
                .build();
    }

    private static JComboBox<Either<SpecialFilterGenderValues, Gender>> createGenderFilter(
            EmployeeTableFilter employeeTableFilter) {
        return FilterComboboxBuilder.create(SpecialFilterGenderValues.class, Gender.values())
                .setSelectedItem(SpecialFilterGenderValues.BOTH)
                .setSpecialValuesRenderer(new SpecialFilterGenderValuesRenderer())
                .setValuesRenderer(new GenderRenderer())
                .setFilter(employeeTableFilter::filterGender)
                .build();
    }

    public void show() {
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        var frame = new JFrame("Employee records");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
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

    private JToolBar createToolbar(Component... components) {
        var toolbar = new JToolBar();
        toolbar.add(quitAction);
        toolbar.addSeparator();
        toolbar.add(addAction);
        toolbar.add(editAction);
        toolbar.add(deleteAction);
        toolbar.addSeparator();

        for (var component : components) {
            toolbar.add(component);
        }

        return toolbar;
    }

    private void changeActionsState(int selectedItemsCount) {
        editAction.setEnabled(selectedItemsCount == 1);
        deleteAction.setEnabled(selectedItemsCount >= 1);
    }
}
