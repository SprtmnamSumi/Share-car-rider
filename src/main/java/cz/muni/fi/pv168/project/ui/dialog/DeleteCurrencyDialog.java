package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import javax.swing.JComboBox;

class DeleteCurrencyDialog extends EntityDialog<Currency> {
    private final JComboBox<Currency> currencyComboBoxModel;
    private Currency currency = null;

    public DeleteCurrencyDialog(EntityListModelAdapter<Currency> currencyTable) {
        currencyComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(currencyTable));
        addFields();
        ValidableListener validableListener = new ValidableListener() {
            @Override
            protected void onChange(boolean isValid) {
                DeleteCurrencyDialog.super.toggleOk(isValid);
            }
        };
        validableListener.fireChange();

        currencyComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                currency = (Currency) e.getItem();
            }
        });
    }

    private void addFields() {
        add("Currency", currencyComboBoxModel);
    }

    @Override
    Currency getEntity() {
        return currency;
    }

}
