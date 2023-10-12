package cz.muni.fi.pv168.employees.ui;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.action.*;
import cz.muni.fi.pv168.employees.ui.filters.EmployeeTableFilter;
import cz.muni.fi.pv168.employees.ui.filters.components.FilterComboboxBuilder;
import cz.muni.fi.pv168.employees.ui.filters.values.SpecialFilterGenderValues;
import cz.muni.fi.pv168.employees.ui.model.*;
import cz.muni.fi.pv168.employees.ui.panels.CarRideTablePanel;
import cz.muni.fi.pv168.employees.ui.panels.EmployeeListPanel;
import cz.muni.fi.pv168.employees.ui.panels.EmployeeTablePanel;
import cz.muni.fi.pv168.employees.ui.renderers.GenderRenderer;
import cz.muni.fi.pv168.employees.ui.renderers.SpecialFilterGenderValuesRenderer;
import cz.muni.fi.pv168.employees.util.Either;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MainWindow {

    private final JFrame frame;
    private final Action quitAction = new QuitAction();
    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;

    private final Action settingsAction;
    private final Action currenciesAction;
    private final Action importAction;
    private final Action exportAction;
    private final Action info;

    public MainWindow() {
        frame = createFrame();
        var testDataGenerator = new TestDataGenerator();

        var departmentListModel = new DepartmentListModel(testDataGenerator.getDepartments());
        var employeeTableModel = new EmployeeTableModel(testDataGenerator.createTestEmployees(10));
        var employeeListModel = new EntityListModelAdapter<>(employeeTableModel);
        var employeeTablePanel = new EmployeeTablePanel(employeeTableModel, departmentListModel, this::changeActionsState);
        var employeeListPanel = new EmployeeListPanel(employeeListModel);
        var templateListPanel = new EmployeeListPanel(employeeListModel);

        var carRideTableModel = new CarRideTableModel(testDataGenerator.createTestRides(10));
        var carRideListModel = new EntityListModelAdapter<>(carRideTableModel);
        var carRideTablePanel = new CarRideTablePanel(carRideTableModel, departmentListModel, this::changeActionsState);

        addAction = new AddAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        deleteAction = new DeleteAction(employeeTablePanel.getTable());
        editAction = new EditAction(employeeTablePanel.getTable(), departmentListModel);
        settingsAction = new SettingsAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        currenciesAction = new currenciesAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        importAction = new ImportAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        exportAction = new ExportAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        info = new InfoAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);

        employeeTablePanel.setComponentPopupMenu(createEmployeeTablePopupMenu());

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", employeeTablePanel, new ButtonTabComponent(tabbedPane, addAction));
        tabbedPane.addSpecialTab("Categories", employeeListPanel, new ButtonTabComponent(tabbedPane, addAction));
        tabbedPane.addSpecialTab("Templates", templateListPanel, new ButtonTabComponent(tabbedPane, addAction));

        frame.add(tabbedPane, BorderLayout.CENTER);


        var rowSorter = new TableRowSorter<>(employeeTableModel);
        var employeeTableFilter = new EmployeeTableFilter(rowSorter);
        employeeTablePanel.getTable().setRowSorter(rowSorter);

        var genderFilter = createGenderFilter(employeeTableFilter);

//        frame.add(createToolbar(genderFilter), BorderLayout.BEFORE_FIRST_LINE);
        frame.setJMenuBar(createMenuBar());
        frame.add(new JLabel("Total distance"), BorderLayout.PAGE_END);
        frame.pack();
        changeActionsState(0);
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
        var frame = new JFrame("Share Car Ride");
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


    private JMenu editBar() {
        var editMenu = new JMenu("File");
        editMenu.setMnemonic('f');

        editMenu.add(settingsAction);
        editMenu.addSeparator();

        editMenu.add(currenciesAction);
        editMenu.addSeparator();

        editMenu.add(importAction);
        editMenu.add(exportAction);
        editMenu.addSeparator();

        editMenu.add(quitAction);

        return editMenu;
    }

    private JMenu helpBar() {
        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('h');
        helpMenu.add(info);
        return helpMenu;
    }

    private JMenuBar createMenuBar() {
        var menuBar = new JMenuBar();
        var editBar = editBar();
        menuBar.add(editBar);
        var helpBar = helpBar();
        menuBar.add(helpBar);
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
