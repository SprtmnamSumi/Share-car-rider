package cz.muni.fi.pv168.project.ui.action.Currency;

import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;

final class DeleteCurrencyAction extends AbstractAction {
    private final JTable currencyTable;
    DeleteCurrencyAction(JTable currencyTable, Icon icon) {
        super("Delete");
        this.currencyTable = currencyTable;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Deletes Currency");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currencyTableModel = (CurrencyTableModel) currencyTable.getModel();
        Arrays.stream(currencyTable.getSelectedRows())
                .map(currencyTable::convertRowIndexToModel)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(currencyTableModel::deleteRow);
    }
}
