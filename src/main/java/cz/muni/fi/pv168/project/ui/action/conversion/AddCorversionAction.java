package cz.muni.fi.pv168.project.ui.action.conversion;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.AddCurrencyDialog;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddCorversionAction extends AbstractAction {
    private final JTable corversionTable;

    public AddCorversionAction(JTable corversionTable) {
        super("Add", Icons.ADD_ICON);
        this.corversionTable = corversionTable;
        putValue(SHORT_DESCRIPTION, "Adds new Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) corversionTable.getModel();
        var dialog = new AddCurrencyDialog(new Currency("", "", 0.0));
        dialog.show(corversionTable, "Add Currency")
                .ifPresent(currencyTableModel::addRow);
    }
}
