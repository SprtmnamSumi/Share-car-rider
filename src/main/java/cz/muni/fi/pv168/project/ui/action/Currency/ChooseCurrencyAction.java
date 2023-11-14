package cz.muni.fi.pv168.project.ui.action.Currency;


import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public final class ChooseCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final DialogFactory dialogFactory;
    private final CurrencyActionFactory currencyActionFactory;

    public ChooseCurrencyAction(JTable currencyTable, DialogFactory dialogFactory, CurrencyActionFactory currencyActionFactory, Icon icon) {
        super("Choose currency");

        this.currencyTable = currencyTable;
        this.dialogFactory = dialogFactory;
        this.currencyActionFactory = currencyActionFactory;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Choose Currency");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        dialogFactory.getChooseCurrencyDialog(currencyTable, currencyActionFactory)
                .show(currencyTable, "Choose Currency", "Choose")
                .ifPresent(currencyTableModel::addRow);
    }
}
