package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.action.Currency.AddCurrencyAction;
import cz.muni.fi.pv168.project.ui.action.Currency.CurrencyActionFactory;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.model.ListModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChooseCurrencyDialog extends EntityDialog<Currency> {
    private final Currency currency;
    private final ListModel<Currency> currencyListModel;
    private final JComboBox<Currency> currencyCombo;
    private final JButton addCurrencyButton;

    public ChooseCurrencyDialog(JTable currencyTable, Currency currency, ListModel<Currency> currencyListModel) {
        this.currency = currency;
        this.currencyListModel = currencyListModel;
        this.currencyCombo = new JComboBox<>(new ComboBoxModelAdapter<>(currencyListModel));

        addCurrencyButton = new JButton("Select a file");
        addCurrencyButton.addActionListener(new AddCurrencyAction(currencyTable));

        addFields();
    }

    private void addFields() {
        add("Currency", currencyCombo);
        add("", addCurrencyButton);
    }

    @Override
    Currency getEntity() {
        return currency;
    }
}