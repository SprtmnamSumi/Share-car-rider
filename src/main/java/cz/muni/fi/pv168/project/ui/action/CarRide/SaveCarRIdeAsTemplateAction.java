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
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class SaveCarRIdeAsTemplateAction extends AbstractAction {

    private final JTable carRidesTable;
    private final ListModel<Category> categoriestListModel;
    private final ListModel<Currency> currencyListModel;
    private final ListModel<Template> carRideTemplateListModel;
    private final TableModel<Template> repository;
    private final DefaultActionFactory<Category> categoryActionFactory;
    private final CategoryTableModel categoryTableModel;
    private final CurrencyConverter currencyConverter;
    private BufferedImage editImage;


    SaveCarRIdeAsTemplateAction(JTable carRidesTable, ListModel<Category> categoriestListModel, EntityListModelAdapter<Currency> currencyListModel, ListModel<Template> carRideTemplateListModel, TableModel<Template> repository, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableModel, CurrencyConverter currencyConverter) {
        super("Save as template");

        try {
            editImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/write.png"));
            Icon customIcon = new ImageIcon(editImage);
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
        this.categoryTableModel = categoryTableModel;
        this.currencyConverter = currencyConverter;
        putValue(SHORT_DESCRIPTION, "Save as a template");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl t"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = carRidesTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (carRidesTable.isEditing()) {
            carRidesTable.getCellEditor().cancelCellEditing();
        }
        CarRideTableModel carRideTableModel = (CarRideTableModel) carRidesTable.getModel();
        int modelRow = carRidesTable.convertRowIndexToModel(selectedRows[0]);
        CarRide carRide = carRideTableModel.getEntity(modelRow);

        CarRideDialog CarRideDialog = new CarRideDialog(carRide, categoriestListModel, currencyListModel, carRideTemplateListModel, repository, categoryActionFactory, categoryTableModel, currencyConverter);

        CarRideDialog.show(carRidesTable, "Edit Car Ride")
                .ifPresent(carRideTableModel::updateRow);
    }
}
