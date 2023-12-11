package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;

class CurrencyDialog extends EntityDialog<Currency> {
    private final ValidatedInputField nameTextField = new ValidatedInputField(ValidatorFactory.stringValidator(1, 150));
    private final ValidatedInputField symbolTextField = new ValidatedInputField(ValidatorFactory.stringValidator(1, 3));
    private final ValidatedInputField rateToDollar = new ValidatedInputField(ValidatorFactory
            .eitherValidator(ValidatorFactory.doubleValidator(), ValidatorFactory.intValidator()));
    private final Currency currency;

    public CurrencyDialog(Currency currency) {
        this.currency = currency;
        setValues();
        addFields();
        ValidableListener validableListener = new ValidableListener() {
            @Override
            protected void onChange(boolean isValid) {
                CurrencyDialog.super.toggleOk(isValid);
            }
        };
        validableListener.fireChange();
        validableListener.setListeners(nameTextField, symbolTextField, rateToDollar);
    }

    private void setValues() {
        nameTextField.setText(currency.getName());
        symbolTextField.setText(currency.getSymbol());
        rateToDollar.setText(String.valueOf(currency.getNewestRateToDollar()));
    }

    private void addFields() {
        add("name", nameTextField);
        add("symbol", symbolTextField);
        add("rate to dollar", rateToDollar);
    }

    @Override
    Currency getEntity() {
        currency.setName(nameTextField.getText());
        currency.setSymbol(symbolTextField.getText());
        currency.setNewestRateToDollar(Double.parseDouble(rateToDollar.getText()));
        return currency;
    }
}
