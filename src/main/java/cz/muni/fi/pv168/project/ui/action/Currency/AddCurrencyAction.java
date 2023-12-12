package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.table.CurrencyTableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


final class AddCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final EntityProvider entityProvider;
    private final DialogFactory dialogFactory;
    AddCurrencyAction(JTable currencyTable, DialogFactory dialogFactory, EntityProvider entityProvider, Icon icon) {
        super("Add");
        this.dialogFactory = dialogFactory;
        this.entityProvider = entityProvider;
        this.currencyTable = currencyTable;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Adds new CurrencyEntity");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        dialogFactory.getAddCurrencyDialog(entityProvider.getCurrency())
                .show(currencyTable, "Add CurrencyEntity", "Add")
                .ifPresent(currencyTableModel::addRow);
    }
}
