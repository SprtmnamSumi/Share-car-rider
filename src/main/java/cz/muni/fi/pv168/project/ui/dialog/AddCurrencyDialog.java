package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.validation.ValidationUtils;
import cz.muni.fi.pv168.project.util.ConversionUtils;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidationListener;

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
            return (ValidationUtils.validateDouble(this) && Double.parseDouble(this.getText()) > 0)
                    || (ValidationUtils.validateInteger(this) && Integer.parseInt(this.getText()) > 0);
        }
    };

    private final ValidationListener validationListener = new ValidationListener(nameTextField, symbolTextField, rateToDollar) {
        @Override
        protected void onChange(boolean isValid) {
            AddCurrencyDialog.super.toggleOk(isValid);
        }
    };
    private final Currency currency;

    public AddCurrencyDialog(Currency currency) {
        this.currency = currency;
        //setValues();

        addFields();
        validationListener.fireChange();
    }

    private void setValues() {
        nameTextField.setText(currency.getName());
        symbolTextField.setText(currency.getSymbol());
        rateToDollar.setText(String.valueOf(currency.getNewestRateToDollar()));
    }

    private void addFields() {
        add("Currency name", nameTextField);
        add("Currency symbol", symbolTextField);
        add("Currency rate to dollar", rateToDollar);
    }

    @Override
    Currency getEntity() {
        return new Currency(nameTextField.getText(), symbolTextField.getText(), Double.parseDouble(rateToDollar.getText()));
    }
}
