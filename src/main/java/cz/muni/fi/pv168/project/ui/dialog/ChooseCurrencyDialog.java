package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;


class ChooseCurrencyDialog extends EntityDialog<Currency> {
    private final JComboBox<Currency> currencyCombox;
    private final JButton addCurrencyButton;

    ChooseCurrencyDialog(JTable currencyTable, EntityListModelAdapter<Currency> currencyListModel, CurrencyActionFactory actionFactory) {

        this.currencyCombox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyListModel));

        addCurrencyButton = new JButton("Add new currency");
        addCurrencyButton.addActionListener(actionFactory.getAddAction(currencyTable));

        addFields();
    }

    private void addFields() {
        add("Currency", currencyCombox);
        add("", addCurrencyButton);
    }

    @Override
    Currency getEntity() {
        Currency currency = (Currency) currencyCombox.getSelectedItem();
        return currency;
    }
}