package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;


final class AddCurrencyAction extends AbstractAction {
    private final JTable currencyTable;

    private final DialogFactory dialogFactory;

    AddCurrencyAction(JTable currencyTable, DialogFactory dialogFactory, Icon icon) {
        super("Add");
        this.dialogFactory = dialogFactory;
        this.currencyTable = currencyTable;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Adds new Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        dialogFactory.getAddCurrencyDialog(new Currency("", "", 0.0))
                .show(currencyTable, "Add Currency", "Add")
                .ifPresent(currencyTableModel::addRow);
    }
}
