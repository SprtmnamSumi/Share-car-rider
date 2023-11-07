package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChooseCurrencyDialog extends EntityDialog<Currency> {
    private final Currency currency;
    private final ListModel<Currency> currencyListModel;
    private final JComboBox<Currency> currencyCombo;

    public ChooseCurrencyDialog(Currency currency, ListModel<Currency> currencyListModel) {
        this.currency = currency;
        this.currencyListModel = currencyListModel;
        this.currencyCombo = new JComboBox<>(new ComboBoxModelAdapter<>(currencyListModel));
    }

    private void addFields() {
        add("Currency", currencyCombo);
    }

    @Override
    Currency getEntity() {
        return currency;
    }
}