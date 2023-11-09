package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.CarRideDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class EditCarRideAction extends AbstractAction {

    private final JTable carRidesTable;
    private final ListModel<Category> categoriestListModel;
    private final ListModel<Currency> currencyListModel;
    private final ListModel<Template> carRideTemplateListModel;
    private final TableModel<Template> repository;


    EditCarRideAction(JTable carRidesTable, ListModel<Category> categoriestListModel, EntityListModelAdapter<Currency> currencyListModel, ListModel<Template> carRideTemplateListModel, TableModel<Template> repository) {
        super("Edit", Icons.EDIT_ICON);
        this.carRidesTable = carRidesTable;
        this.currencyListModel = currencyListModel;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        this.repository = repository;
        putValue(SHORT_DESCRIPTION, "Edits Car Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
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

        CarRideDialog CarRideDialog = new CarRideDialog(categoriestListModel, currencyListModel, carRideTemplateListModel, repository);

        CarRideDialog.show(carRidesTable, "Edit Car Ride")
                .ifPresent(carRideTableModel::updateRow);
    }
}
