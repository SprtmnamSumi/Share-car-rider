package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.business.model.*;
import cz.muni.fi.pv168.project.business.service.crud.CarRideCrudService;
import cz.muni.fi.pv168.project.business.service.crud.CarRideTemplateCrudService;
import cz.muni.fi.pv168.project.business.service.crud.CategoryCrudService;
import cz.muni.fi.pv168.project.business.service.validation.CarRideTemplateValidator;
import cz.muni.fi.pv168.project.business.service.validation.CarRideValidator;
import cz.muni.fi.pv168.project.business.service.validation.CategoryValidator;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.storage.InMemoryRepository;
import cz.muni.fi.pv168.project.ui.action.CarRide.AddCarRideAction;
import cz.muni.fi.pv168.project.ui.action.CarRide.DeleteCarRideAction;
import cz.muni.fi.pv168.project.ui.action.CarRide.EditCarRideAction;
import cz.muni.fi.pv168.project.ui.action.Category.AddCategoryAction;
import cz.muni.fi.pv168.project.ui.action.*;
import cz.muni.fi.pv168.project.ui.action.Templates.AddTemplateAction;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.model.common.ButtonTabComponent;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Template.TemplateTablePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TabPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class MainWindowImpl implements MainWindow{

    private final JFrame frame;
    private final Action quitAction = new QuitAction();
    private final Action addCarRideAction;
    private final Action editCarRideAction;
    private final Action deleteCarRideAction;

    private final Action addCategory;
    private final Action settingsAction;
    private final Action currenciesAction;
    private final Action importAction;
    private final Action exportAction;
    private final Action addTemplate;
    private final Action info;

    private final EntityListModelAdapter<CarRide> carRideListModel;
    private final EntityListModelAdapter<Template> templateListModel;
    private final EntityListModelAdapter<Category> categoryListModel;
    @Inject
    public MainWindowImpl(CarRideTableModel carRideTableModel,
                          TemplateTableModel templateTableModel,
                          CategoryTableModel categoryTableModel) {
        frame = createFrame();

        carRideListModel = new EntityListModelAdapter<>(carRideTableModel);
        templateListModel = new EntityListModelAdapter<>(templateTableModel);
        categoryListModel = new EntityListModelAdapter<>(categoryTableModel);

        var carRideTablePanel = new CarRideTablePanel(carRideTableModel, categoryListModel, templateListModel);
        var templateTablePanel = new TemplateTablePanel(templateTableModel, categoryListModel, templateListModel);
        var categoryTablePanel = new CategoryTablePanel(categoryTableModel, categoryListModel);

        addCategory = new AddCategoryAction(categoryTablePanel.getTable(), categoryListModel);

        addCarRideAction = new AddCarRideAction(carRideTablePanel.getTable(), categoryListModel, templateListModel);
        editCarRideAction = new EditCarRideAction(carRideTablePanel.getTable(), categoryListModel, templateListModel);
        deleteCarRideAction = new DeleteCarRideAction(carRideTablePanel.getTable());


        addTemplate = new AddTemplateAction(templateTablePanel.getTable(), categoryListModel, templateListModel);
        settingsAction = new SettingsAction();
        currenciesAction = new InfoAction.currenciesAction();
        importAction = new ImportAction();
        exportAction = new ExportAction();
        info = new InfoAction();

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", carRideTablePanel, new ButtonTabComponent(tabbedPane, addCarRideAction, "Add new ride"));
        tabbedPane.addSpecialTab("Categories", categoryTablePanel, new ButtonTabComponent(tabbedPane, addCategory, "Add new category"));
        tabbedPane.addSpecialTab("Templates", templateTablePanel, new ButtonTabComponent(tabbedPane, addTemplate, "Add new template"));

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setJMenuBar(createMenuBar());
        frame.pack();
    }
    @Override
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
}
