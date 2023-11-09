package cz.muni.fi.pv168.project.ui.action.Currency;


import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.ChooseCurrencyDialog;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class ChooseCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final EntityListModelAdapter<Currency> currencyListModel;

    public ChooseCurrencyAction(JTable currencyTable, EntityListModelAdapter<Currency> currencyListModel) {
        super("Choose currency", null);
        this.currencyTable = currencyTable;
        this.currencyListModel = currencyListModel;
        putValue(SHORT_DESCRIPTION, "Choose Currency");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new ChooseCurrencyDialog(currencyTable, new Currency("", "", 0.0), currencyListModel);
        dialog.show(currencyTable, "Choose Currency");
    }
}
