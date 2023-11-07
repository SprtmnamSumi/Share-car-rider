package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.dialog.AddCurrencyDialog;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class AddCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final ListModel<Currency> currencyListModel;

    AddCurrencyAction(JTable currencyTable, EntityListModelAdapter<Currency> currencyListModel) {
        super("Add", Icons.ADD_ICON);
        this.currencyTable = currencyTable;
        this.currencyListModel = currencyListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CurrencyTableModel) currencyTable.getModel();
        var dialog = new AddCurrencyDialog(Currency.createCurrency("", "", 0.0), currencyListModel);
        dialog.show(currencyTable, "Add category")
                .ifPresent(categoryTableModel::addRow);
    }
}
