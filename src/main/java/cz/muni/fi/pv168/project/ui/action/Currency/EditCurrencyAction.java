package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.EntityDialog;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;

final class EditCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final DialogFactory dialogFactory;

    EditCurrencyAction(JTable currencyTable, DialogFactory dialogFactory, Icon icon) {
        super("Edit");
        this.currencyTable = currencyTable;
        this.dialogFactory = dialogFactory;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Edits Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = currencyTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (currencyTable.isEditing()) {
            currencyTable.getCellEditor().cancelCellEditing();
        }
        CurrencyTableModel currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        int modelRow = currencyTable.convertRowIndexToModel(selectedRows[0]);
        Currency currency = currencyTableModel.getEntity(modelRow);

        EntityDialog<Currency> currencyDialog = dialogFactory.getAddCurrencyDialog(currency);
        currencyDialog.show(currencyTable, "Edit Currency Ride", "Edit")
                .ifPresent(currencyTableModel::updateRow);
    }
}
