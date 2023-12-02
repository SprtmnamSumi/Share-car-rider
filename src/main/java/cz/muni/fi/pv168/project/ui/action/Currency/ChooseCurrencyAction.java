package cz.muni.fi.pv168.project.ui.action.Currency;


import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

public final class ChooseCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    private final DialogFactory dialogFactory;
    private final CurrencyActionFactory currencyActionFactory;

    public ChooseCurrencyAction(JTable currencyTable, DialogFactory dialogFactory, CurrencyActionFactory currencyActionFactory, Icon icon) {
        super("Add currency");

        this.currencyTable = currencyTable;
        this.dialogFactory = dialogFactory;
        this.currencyActionFactory = currencyActionFactory;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Add CurrencyEntity");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        dialogFactory.getAddCurrencyDialog(new Currency("", "", "", 1.0))
                .show(currencyTable, "Add CurrencyEntity", "Add")
                .ifPresent(currencyTableModel::addRow);
    }
}
