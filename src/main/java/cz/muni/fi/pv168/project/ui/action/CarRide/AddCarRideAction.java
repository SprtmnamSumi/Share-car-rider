package cz.muni.fi.pv168.project.ui.action.CarRide;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.CarRideDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

final class AddCarRideAction extends AbstractAction {

    private final JTable carRidesTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Currency> currencyListModel;
    private final ListModel<Template> carRideTemplateListModel;
    private final TableModel<Template> repository;
    private final DefaultActionFactory<Category> categoryActionFactory;
    private final CategoryTableModel categoryTableMode;
    private final CurrencyConverter currencyConverter;
    private BufferedImage addImage;


    AddCarRideAction(JTable carRidesTable, ListModel<Category> categoriestListModel, ListModel<Currency> currencyListModel, ListModel<Template> carRideTemplateListModel, TableModel<Template> repository, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableMode, CurrencyConverter currencyConverter) {
        super("Add");

        try {
            addImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/add.png"));
            Icon customIcon = new ImageIcon(addImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.carRidesTable = carRidesTable;
        this.currencyListModel = currencyListModel;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        this.repository = repository;
        this.categoryActionFactory = categoryActionFactory;
        this.categoryTableMode = categoryTableMode;
        this.currencyConverter = currencyConverter;
        putValue(SHORT_DESCRIPTION, "Adds new Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    private void addEntity(CarRide carRide) {
        carRide.setCurrencyConversion(carRide.getCurrencyConversion());
        ((CarRideTableModel) carRidesTable.getModel()).addRow(carRide);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var carRide = new CarRide(null, "", "", 0.0, 0, 0, 0, 0, LocalDateTime.now(), null, currencyListModel.getElementAt(0), currencyListModel.getElementAt(0).getConversions().get(0));
        var dialog = new CarRideDialog(carRide, categoriestListModel, currencyListModel, carRideTemplateListModel, repository, categoryActionFactory, categoryTableMode, currencyConverter);
        dialog.show(carRidesTable, "Add Cat ride")
                .ifPresent(this::addEntity);
    }
}
