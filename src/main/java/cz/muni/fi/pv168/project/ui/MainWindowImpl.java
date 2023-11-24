package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.statistics.ICarRideStatistics;
import cz.muni.fi.pv168.project.data.Initializator;
import cz.muni.fi.pv168.project.ui.action.CarRide.ICarRideActionFactory;
import cz.muni.fi.pv168.project.ui.action.ColorThemeAction;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.action.ExportAction;
import cz.muni.fi.pv168.project.ui.action.ImportAction;
import cz.muni.fi.pv168.project.ui.action.InfoAction;
import cz.muni.fi.pv168.project.ui.action.QuitAction;
import cz.muni.fi.pv168.project.ui.action.SettingsAction;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.model.common.ButtonTabComponent;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Template.TemplateTablePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TabPanel;
import java.awt.BorderLayout;
import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.WindowConstants;

class MainWindowImpl implements MainWindow {

    private final JFrame frame;
    private final Action quitAction = new QuitAction();
    private final Action addCarRideAction;
    private final Action addCategory;
    private final Action settingsAction;
    private final Action chooseCurrencyAction;
    private final Action importAction;
    private final Action exportAction;
    private final Action colorThemeAction;
    private final Action addTemplate;
    private final Action info;

    @Inject
    MainWindowImpl(ICarRideActionFactory carActionFactory,
                   DefaultActionFactory<Category> categoryActionFactory,
                   DefaultActionFactory<Template> templateActionFactory,
                   CurrencyActionFactory currencyActionFactory,
                   CarRideTableModel carRideTableModel,
                   CategoryTableModel categoryTableModel,
                   TemplateTableModel templateTableModel,
                   CurrencyTableModel currencyTableModel,
                   ICarRideStatistics ICarRideStatistics
    ) {
        frame = createFrame();

        CarRideTablePanel carRideTablePanel = new CarRideTablePanel(carRideTableModel, carActionFactory, categoryTableModel, currencyTableModel, ICarRideStatistics);
        CategoryTablePanel categoryTablePanel = new CategoryTablePanel(categoryTableModel, categoryActionFactory);
        TemplateTablePanel templateTablePanel = new TemplateTablePanel(templateTableModel, templateActionFactory);

        addCarRideAction = carActionFactory.getAddAction(carRideTablePanel.getTable());
        addCategory = categoryActionFactory.getAddAction(categoryTablePanel.getTable());
        addTemplate = templateActionFactory.getAddAction(templateTablePanel.getTable());

        chooseCurrencyAction = currencyActionFactory.getChooseAction(new JTable(currencyTableModel));

        settingsAction = new SettingsAction();
        importAction = new ImportAction();
        exportAction = new ExportAction(carRideTablePanel.getFilter(), templateTableModel, currencyTableModel, categoryTableModel);
        colorThemeAction = new ColorThemeAction();
        info = new InfoAction();

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", carRideTablePanel, new ButtonTabComponent(tabbedPane, addCarRideAction, "Add new ride"));
        tabbedPane.addSpecialTab("Categories", categoryTablePanel, new ButtonTabComponent(tabbedPane, addCategory, "Add new category"));
        tabbedPane.addSpecialTab("Templates", templateTablePanel, new ButtonTabComponent(tabbedPane, addTemplate, "Add new template"));

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setJMenuBar(createMenuBar());
        frame.pack();

        Initializator init = new Initializator(categoryTableModel, carRideTableModel, currencyTableModel, templateTableModel, 10);
        init.initialize();
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

        JMenu settingsMenu = new JMenu("Settings");
        ImageIcon settingsIcon = new ImageIcon("src/main/java/cz/muni/fi/pv168/project/ui/icons/settings.png");
        editMenu.add(settingsMenu);
        settingsMenu.setIcon(settingsIcon);
        settingsMenu.add(colorThemeAction);
        editMenu.addSeparator();

        editMenu.add(importAction);
        editMenu.add(exportAction);
        editMenu.addSeparator();

        editMenu.add(chooseCurrencyAction);
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
