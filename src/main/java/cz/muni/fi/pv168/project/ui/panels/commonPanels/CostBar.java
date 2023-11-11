package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.validation.ValidationUtils;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidatedJPanel;
import cz.muni.fi.pv168.project.ui.validation.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;


public class CostBar extends ValidatedJPanel {
    private final ValidatedInputField costOfFuel = getDoubleField();
    private final JComboBox<Currency> currencyJComboBox;
    private final CurrencyConverter currencyConverter;
    private final ValidatedInputField rate = getDoubleField();

    public CostBar(ListModel<Currency> currencyModel, CurrencyConverter currencyConverter) {
        super();
        setValidables(costOfFuel, rate);

        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        this.currencyConverter = currencyConverter;
        currencyJComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var curr = (Currency) e.getItem();
                var rate = curr.getNewestRateToDollar();
                setConversionRateToDollars(rate);
            }
        });

        JLabel fuelLabel = new JLabel("Cost of Fuel:");
        this.add(fuelLabel);
        costOfFuel.setPreferredSize(new Dimension(150, 30));
        this.add(costOfFuel);

        JLabel currencyLabel = new JLabel("Currency:");
        this.add(currencyLabel);
        currencyJComboBox.setPreferredSize(new Dimension(150, 30));
        this.add(currencyJComboBox);

        JLabel rateLabel = new JLabel("Rate:");
        this.add(rateLabel);
        rate.setPreferredSize(new Dimension(150, 30));
        this.add(rate);
    }

    public void SetValues(double costOfFuelval, double covertRateval, Currency currency) {
        setCurrency(currency);
        setConversionRateToDollars(covertRateval);
        setCostOfFuelInDollars(costOfFuelval);
    }

    public double getCostOfFuelInDollars() {
        return currencyConverter.ConvertFromConversionRateTODollars(Double.parseDouble(rate.getText()), Double.parseDouble(costOfFuel.getText()));
    }

    public void setCostOfFuelInDollars(double costOfFuel) {
        currencyConverter.convertFromDoolarsToConvRate(Double.parseDouble(rate.getText()), costOfFuel);
        this.costOfFuel.setText(String.valueOf(costOfFuel));
    }

    public double getConversionRateToDollars() {
        return Double.parseDouble(rate.getText());
    }

    public void setConversionRateToDollars(double covertRate) {
        this.rate.setText(String.valueOf(covertRate));
    }

    public Currency getCurrency() {
        return (Currency) currencyJComboBox.getSelectedItem();
    }

    public void setCurrency(Currency currency) {
        this.currencyJComboBox.setSelectedItem(currency);
    }

    private ValidatedInputField getDoubleField() {
        return new ValidatedInputField() {
            @Override
            public boolean evaluate() {
                return ValidationUtils.validateDouble(this)
                        && Double.parseDouble(this.getText()) >= 0.0f;
            }
        };
    }
}

