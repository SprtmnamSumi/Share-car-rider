package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;

import javax.swing.*;

public class AddCurrencyDialog extends EntityDialog<Currency> {
    private final JTextField nameTextField = new JTextField();
    private final JTextField symbolTextField = new JTextField();
    private final JSpinner rateToDollar = new JSpinner(new SpinnerNumberModel());
    private final Currency currency;

    public AddCurrencyDialog(Currency currency, ListModel<Currency> currencyListModel) {
        this.currency = currency;
        setValues();
        addFields();
    }

    private void setValues() {
        nameTextField.setText(currency.getName());
        symbolTextField.setText(currency.getSymbol());
    }

    private void addFields() {
        add("Currency name", nameTextField);
        add("Currency symbol", symbolTextField);
        add("Currency rate to dollar", rateToDollar);
    }

    @Override
    Currency getEntity() {
        return null;
    }
}
