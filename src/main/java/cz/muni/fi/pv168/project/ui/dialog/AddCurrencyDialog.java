package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;
import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;

class AddCurrencyDialog extends EntityDialog<Currency> {
    private final ValidatedInputField nameTextField = new ValidatedInputField(ValidatorFactory.stringValidator(1, 150));
    private final ValidatedInputField symbolTextField = new ValidatedInputField(ValidatorFactory.stringValidator(1, 1));
    private final ValidatedInputField rateToDollar = new ValidatedInputField(ValidatorFactory
            .eitherValidator(ValidatorFactory.doubleValidator(), ValidatorFactory.intValidator()));
    private final Currency currency;

    private final ValidableListener validableListener = new ValidableListener() {
        @Override
        protected void onChange(boolean isValid) {
            AddCurrencyDialog.super.toggleOk(isValid);
        }
    };

    public AddCurrencyDialog(Currency currency) {
        this.currency = currency;
        //setValues();

        addFields();
        validableListener.fireChange();
        validableListener.setListeners(nameTextField, symbolTextField, rateToDollar);
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
