package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.EntityDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class EditCarRideAction extends AbstractAction {

    private final JTable carRidesTable;
    private final DialogFactory modalDialogFactory;

    EditCarRideAction(JTable carRidesTable, DialogFactory modalDialogFactory, Icon icon) {
        super("Edit");
        this.modalDialogFactory = modalDialogFactory;
        this.carRidesTable = carRidesTable;

        putValue(SMALL_ICON, icon);
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

        EntityDialog<CarRide> dialog = modalDialogFactory.getAddCarRideDialog(carRide);
        dialog.show(carRidesTable, "Edit Car Ride", "Save")
                .ifPresent(carRideTableModel::updateRow);
    }
}
