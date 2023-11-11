package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.validation.FieldConversionUtils;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class AddCurrencyDialog extends EntityDialog<Currency> {
    private final ValidatedInputField nameTextField = new ValidatedInputField() {
        @Override
        public boolean evaluate() {
            return !this.getText().isEmpty();
        }
    };
    private final ValidatedInputField symbolTextField = new ValidatedInputField() {
        @Override
        public boolean evaluate() {
            return this.getText().length() == 1;
        }
    };
    private final ValidatedInputField rateToDollar = new ValidatedInputField() {
        @Override
        public boolean evaluate() {
            return (FieldConversionUtils.validateDouble(this) && Double.parseDouble(this.getText()) > 0)
                    || (FieldConversionUtils.validateInteger(this) && Integer.parseInt(this.getText()) > 0);
        }
    };
    private final TypeListener validationListener = new TypeListener();
    private final Currency currency;

    public AddCurrencyDialog(Currency currency) {
        this.currency = currency;
        //setValues();

        addFields();
        nameTextField.addKeyListener(validationListener);
        symbolTextField.addKeyListener(validationListener);
        rateToDollar.addKeyListener(validationListener);
        toggleOk();
    }

    private void setValues() {
        nameTextField.setText("X");
        symbolTextField.setText(currency.getSymbol());
    }

    private void addFields() {
        add("Currency name", nameTextField);
        add("Currency symbol", symbolTextField);
        add("Currency rate to dollar", rateToDollar);
    }

    @Override
    Currency getEntity() {
        return new Currency(nameTextField.getText(), symbolTextField.getText(), 1.0);
    }

    private void toggleOk() {
        AddCurrencyDialog.super.toggleOk(
                List.of(nameTextField, symbolTextField, rateToDollar)
                        .parallelStream().allMatch(ValidatedInputField::evaluate));
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            toggleOk();
        }
    }
}
