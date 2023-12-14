package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.statistics.ICarRideStatistics;
import cz.muni.fi.pv168.project.ui.action.ColorThemeAction;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.action.IOActionFactory;
import cz.muni.fi.pv168.project.ui.action.InfoAction;
import cz.muni.fi.pv168.project.ui.action.QuitAction;
import cz.muni.fi.pv168.project.ui.model.table.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.table.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.table.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.model.common.ButtonTabComponent;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Currency.CurrencyTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Template.TemplateTablePanel;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TabPanel;
import cz.muni.fi.pv168.project.ui.workers.WorkerProvider;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Font;

class MainWindowImpl implements MainWindow {
    private final JFrame frame;
    private final NotificationController notificationController;
    private final Action quitAction = new QuitAction();
    private final Action importAction;
    private final Action exportAction;
    private final Action colorThemeAction;
    private final Action info;

    @Inject
    MainWindowImpl(DefaultActionFactory<CarRide> carActionFactory,
                   DefaultActionFactory<Category> categoryActionFactory,
                   DefaultActionFactory<Template> templateActionFactory,
                   IOActionFactory ioActionFactory,
                   CurrencyActionFactory currencyActionFactory,
                   TableModel<CarRide> carRideTableModel,
                   CategoryTableModel categoryTableModel,
                   TemplateTableModel templateTableModel,
                   CurrencyTableModel currencyTableModel,
                   ICarRideStatistics ICarRideStatistics,
                   WorkerProvider workerProvider
    ) {
        frame = createFrame();

        CarRideTablePanel carRideTablePanel = new CarRideTablePanel(carRideTableModel, carActionFactory, ioActionFactory, categoryTableModel, currencyTableModel, ICarRideStatistics);
        CategoryTablePanel categoryTablePanel = new CategoryTablePanel(categoryTableModel, categoryActionFactory, ioActionFactory);
        TemplateTablePanel templateTablePanel = new TemplateTablePanel(templateTableModel, templateActionFactory, ioActionFactory);
        CurrencyTablePanel currencyTablePanel = new CurrencyTablePanel(currencyTableModel, currencyActionFactory, ioActionFactory);

        Action addCarRideAction = carActionFactory.getAddAction(carRideTablePanel.getTable());
        Action addCategory = categoryActionFactory.getAddAction(categoryTablePanel.getTable());
        Action addTemplate = templateActionFactory.getAddAction(templateTablePanel.getTable());
        Action addCurrency = currencyActionFactory.getAddAction(currencyTablePanel.getTable());

        importAction = ioActionFactory.getImportAction();
        exportAction = ioActionFactory.getExportAction(carRideTablePanel.getFilter());
        colorThemeAction = new ColorThemeAction();
        info = new InfoAction();

        var tabbedPane = new TabPanel();

        tabbedPane.addSpecialTab("Car Rides", carRideTablePanel, new ButtonTabComponent(tabbedPane, addCarRideAction, "Add new ride"));
        tabbedPane.addSpecialTab("Categories", categoryTablePanel, new ButtonTabComponent(tabbedPane, addCategory, "Add new category"));
        tabbedPane.addSpecialTab("Templates", templateTablePanel, new ButtonTabComponent(tabbedPane, addTemplate, "Add new template"));
        tabbedPane.addSpecialTab("Currencies", currencyTablePanel, new ButtonTabComponent(tabbedPane, addCurrency, "Add new currency"));

        notificationController = new NotificationController(frame);
        carRideTableModel.addTableModelListener((e) -> notificationController.showTableNotification(carRideTablePanel.getTable(), e));
        categoryTableModel.addTableModelListener((e) -> notificationController.showTableNotification(categoryTablePanel.getTable(), e));
        templateTableModel.addTableModelListener((e) -> notificationController.showTableNotification(templateTablePanel.getTable(), e));
        currencyTableModel.addTableModelListener((e) -> notificationController.showTableNotification(currencyTablePanel.getTable(), e));

        workerProvider.setListener(notificationController);

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

        JMenu settingsMenu = new JMenu("Settings");
        @SuppressWarnings("SpellCheckingInspection") ImageIcon settingsIcon = new ImageIcon("src/main/java/cz/muni/fi/pv168/project/ui/icons/settings.png");
        editMenu.add(settingsMenu);
        settingsMenu.setIcon(settingsIcon);
        settingsMenu.add(colorThemeAction);
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

    private JLabel getBaseCurrencySymbol(){
        var currencySymbol = new JLabel("$");
        currencySymbol.setBorder(BorderFactory.createEmptyBorder(0,4,0,4));
        currencySymbol.setFont(new Font("Arial", Font.PLAIN, 13));
        currencySymbol.setToolTipText("Base currency");
        return currencySymbol;
    }

    private JMenuBar createMenuBar() {
        var menuBar = new JMenuBar();
        var editBar = editBar();
        menuBar.add(editBar);
        var helpBar = helpBar();
        menuBar.add(helpBar);
        menuBar.add(getBaseCurrencySymbol());
        return menuBar;
    }
}
