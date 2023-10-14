package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.old.Department;
import cz.muni.fi.pv168.project.entities.old.Gender;
import cz.muni.fi.pv168.project.ui.action.CarRide.AddRideAction;
import cz.muni.fi.pv168.project.ui.action.Category.AddCategoryAction;
import cz.muni.fi.pv168.project.ui.action.*;
import cz.muni.fi.pv168.project.ui.action.Templates.AddTemplateAction;
import cz.muni.fi.pv168.project.ui.action.old.tobeused.DeleteAction;
import cz.muni.fi.pv168.project.ui.action.old.tobeused.EditAction;
import cz.muni.fi.pv168.project.ui.filters.OLDEmployeeTableFilter;
import cz.muni.fi.pv168.project.ui.filters.OLDvalues.SpecialFilterDepartmentValues;
import cz.muni.fi.pv168.project.ui.filters.OLDvalues.SpecialFilterGenderValues;
import cz.muni.fi.pv168.project.ui.filters.components.FilterComboboxBuilder;
import cz.muni.fi.pv168.project.ui.filters.components.FilterListModelBuilder;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideListModel;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.OLDEmployeeTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.model.common.ButtonTabComponent;
import cz.muni.fi.pv168.project.ui.model.old.DepartmentListModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.panels.OLD.CategoryTablePanel;
import cz.muni.fi.pv168.project.ui.panels.OLD.EmployeeListPanel;
import cz.muni.fi.pv168.project.ui.panels.OLD.EmployeeTablePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TabPanel;
import cz.muni.fi.pv168.project.ui.renderers.OLD.DepartmentRenderer;
import cz.muni.fi.pv168.project.ui.renderers.OLD.GenderRenderer;
import cz.muni.fi.pv168.project.ui.renderers.OLD.SpecialFilterDepartmentValuesRenderer;
import cz.muni.fi.pv168.project.ui.renderers.OLD.SpecialFilterGenderValuesRenderer;
import cz.muni.fi.pv168.project.util.Either;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MainWindow {

    private final JFrame frame;
    private final Action quitAction = new QuitAction();
    private final Action addCarRide;
    private final Action addCategory;
    private final Action deleteAction;
    private final Action editAction;

    private final Action settingsAction;
    private final Action currenciesAction;
    private final Action importAction;
    private final Action exportAction;

    private final Action addTemplate;
    private final Action info;

    public MainWindow() {
        frame = createFrame();
        var testDataGenerator = new TestDataGenerator();

        // OLD START
        var departmentListModel = new DepartmentListModel(testDataGenerator.getDepartments());

        var employeeTableModel = new OLDEmployeeTableModel(testDataGenerator.createTestEmployees(10));
        var employeeListModel = new EntityListModelAdapter<>(employeeTableModel);
        var employeeTablePanel = new EmployeeTablePanel(employeeTableModel, departmentListModel, this::changeActionsState);
        var employeeListPanel = new EmployeeListPanel(employeeListModel);

        var templateListModel = new CarRideListModel(testDataGenerator.createTestTemplates(10));

        var templateListPanel = new EmployeeListPanel(employeeListModel);
        deleteAction = new DeleteAction(employeeTablePanel.getTable());
        editAction = new EditAction(employeeTablePanel.getTable(), departmentListModel);
        settingsAction = new SettingsAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        currenciesAction = new InfoAction.currenciesAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        importAction = new ImportAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        exportAction = new ExportAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);

        info = new InfoAction(employeeTablePanel.getTable(), testDataGenerator, departmentListModel);
        // OLD END

        var categoryTableModel = new CategoryTableModel(testDataGenerator.createTestCategories(10));
        var categoryListModel = new CategoryListModel(testDataGenerator.createTestCategories(10));
        var categoryTablePanel = new CategoryTablePanel(categoryTableModel, this::changeActionsState);
//        var categoryListPanel = new CategoryListPanel(categoryListModel);
        addCategory = new AddCategoryAction(categoryTablePanel.getTable(), testDataGenerator);

        var carRideTableModel = new CarRideTableModel(testDataGenerator.createTestRides(10));
        var carRideTablePanel = new CarRideTablePanel(carRideTableModel, categoryListModel, this::changeActionsState);
//        var carRideListModel = new EntityListModelAdapter<>(carRideTableModel);
//        var carRideListPanel = new CarRideListPanel(carRideListModel);
        addCarRide = new AddRideAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel, templateListModel);

        addTemplate = new AddTemplateAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel, templateListModel);
        employeeTablePanel.setComponentPopupMenu(createEmployeeTablePopupMenu());

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", carRideTablePanel, new ButtonTabComponent(tabbedPane, addCarRide, "Add new ride"));
        tabbedPane.addSpecialTab("Categories", categoryTablePanel, new ButtonTabComponent(tabbedPane, addCategory, "Add new category"));
        tabbedPane.addSpecialTab("Templates", templateListPanel, new ButtonTabComponent(tabbedPane, addTemplate, "Add new template"));

        frame.add(tabbedPane, BorderLayout.CENTER);


        var rowSorter = new TableRowSorter<>(employeeTableModel);
        var employeeTableFilter = new OLDEmployeeTableFilter(rowSorter);
        employeeTablePanel.getTable().setRowSorter(rowSorter);

        var genderFilter = createGenderFilter(employeeTableFilter);
        var departmentFilter = new JScrollPane(createDepartmentFilter(employeeTableFilter, departmentListModel));


        //frame.add(createToolbar(genderFilter, departmentFilter), BorderLayout.BEFORE_FIRST_LINE);

        frame.setJMenuBar(createMenuBar());
        JLabel jlabel = new JLabel("Total distance");
        jlabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(jlabel, BorderLayout.PAGE_END);
        frame.pack();
        changeActionsState(0);
    }

    private static JList<Either<SpecialFilterDepartmentValues, Department>> createDepartmentFilter(
            OLDEmployeeTableFilter employeeTableFilter, DepartmentListModel departmentListModel) {
        return FilterListModelBuilder.create(SpecialFilterDepartmentValues.class, departmentListModel)
                .setSelectedIndex(0)
                .setVisibleRowsCount(3)
                .setSpecialValuesRenderer(new SpecialFilterDepartmentValuesRenderer())
                .setValuesRenderer(new DepartmentRenderer())
                .setFilter(employeeTableFilter::filterDepartment)
                .build();
    }

    private static JComboBox<Either<SpecialFilterGenderValues, Gender>> createGenderFilter(
            OLDEmployeeTableFilter employeeTableFilter) {
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
        menu.add(addCarRide);
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
        toolbar.add(addCarRide);
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
