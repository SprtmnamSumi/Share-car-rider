package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.action.CarRide.AddRideAction;
import cz.muni.fi.pv168.project.ui.action.Category.AddCategoryAction;
import cz.muni.fi.pv168.project.ui.action.*;
import cz.muni.fi.pv168.project.ui.action.Templates.AddTemplateAction;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideListModel;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.common.ButtonTabComponent;
import cz.muni.fi.pv168.project.ui.model.templates.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.panels.CategoryTablePanel;
import cz.muni.fi.pv168.project.ui.panels.TemplateablePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TabPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private final JFrame frame;
    private final Action quitAction = new QuitAction();
    private final Action addCarRide;
    private final Action addCategory;
//    private final Action deleteAction;
//    private final Action editAction;

    private final Action settingsAction;
    private final Action currenciesAction;
    private final Action importAction;
    private final Action exportAction;
    //
    private final Action addTemplate;
    private final Action info;

    public MainWindow() {
        frame = createFrame();
        var testDataGenerator = new TestDataGenerator();
        var templateListModel = new CarRideListModel(testDataGenerator.createTestTemplates(10));


        var categoryTableModel = new CategoryTableModel(testDataGenerator.createTestCategories(10));
        var categoryListModel = new CategoryListModel(testDataGenerator.createTestCategories(10));
        var categoryTablePanel = new CategoryTablePanel(categoryTableModel, this::changeActionsState);
//        var categoryListPanel = new CategoryListPanel(categoryListModel);
        addCategory = new AddCategoryAction(categoryTablePanel.getTable(), testDataGenerator);

        var templateTableModel = new TemplateTableModel(testDataGenerator.createTestTemplates(10));
        var templateRideTablePanel = new TemplateablePanel(templateTableModel, categoryListModel, this::changeActionsState);
//        var carRideListModel = new EntityListModelAdapter<>(carRideTableModel);
//        var carRideListPanel = new CarRideListPanel(carRideListModel);


        var carRideTableModel = new CarRideTableModel(testDataGenerator.createTestRides(10));
        var carRideTablePanel = new CarRideTablePanel(carRideTableModel, categoryListModel, this::changeActionsState);
//        var carRideListModel = new EntityListModelAdapter<>(carRideTableModel);
//        var carRideListPanel = new CarRideListPanel(carRideListModel);
        addCarRide = new AddRideAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel, templateListModel);


        addTemplate = new AddTemplateAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel, templateListModel);
        settingsAction = new SettingsAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel);
        currenciesAction = new InfoAction.currenciesAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel);
        importAction = new ImportAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel);
        exportAction = new ExportAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel);
        info = new InfoAction(carRideTablePanel.getTable(), testDataGenerator, categoryListModel);

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", carRideTablePanel, new ButtonTabComponent(tabbedPane, addCarRide, "Add new ride"));
        tabbedPane.addSpecialTab("Categories", categoryTablePanel, new ButtonTabComponent(tabbedPane, addCategory, "Add new category"));
        tabbedPane.addSpecialTab("Templates", templateRideTablePanel, new ButtonTabComponent(tabbedPane, addTemplate, "Add new template"));

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        changeActionsState(0);
    }

    public void show() {
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        var frame = new JFrame("Share Car Ride");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
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


    private void changeActionsState(int selectedItemsCount) {
//        editAction.setEnabled(selectedItemsCount == 1);
//        deleteAction.setEnabled(selectedItemsCount >= 1);
    }
}
