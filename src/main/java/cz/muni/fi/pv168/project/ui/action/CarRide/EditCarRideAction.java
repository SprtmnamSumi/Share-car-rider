package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.CarRideDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class EditCarRideAction extends AbstractAction {

    private final JTable carRidesTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Template> carRideTemplateListModel;

    public EditCarRideAction(JTable carRidesTable, ListModel<Category> categoriestListModel, ListModel<Template> carRideTemplateListModel) {
        super("Edit", Icons.EDIT_ICON);
        this.carRidesTable = carRidesTable;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
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
        CarRideDialog CarRideDialog = new CarRideDialog(carRide, categoriestListModel, carRideTemplateListModel);
        CarRideDialog.show(carRidesTable, "Edit Car Ride")
                .ifPresent(carRideTableModel::updateRow);
    }
}
