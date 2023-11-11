package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.CurrencyConversion;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;


public class CostBar extends JPanel {
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JComboBox<Currency> currencyJComboBox;
    JSpinner rate = new JSpinner(new SpinnerNumberModel());

    public CostBar(ListModel<Currency> currencyModel, double costOfFuelval, double covertRateval, Currency currency) {
        super(new FlowLayout(FlowLayout.CENTER));

        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        currencyJComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var curr = (Currency) e.getItem();
                var rate = curr.getRateToDollar();
                setConversionRate(rate);
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

        setCostOfFuel(costOfFuelval);
        setConversionRate(covertRateval);
        setCurrency(currency);
    }

    public double getCostOfFuel() {
        return (double) costOfFuel.getValue();
    }

    public void setCostOfFuel(double costOfFuel) {
        this.costOfFuel.setValue(costOfFuel);
    }

    public double getConversionRate() {
        return (double) costOfFuel.getValue();
    }

    public void setConversionRate(double covertRate) {
        this.rate.setValue(covertRate);
    }

    public CurrencyConversion getobjectCOnversionRate() {
        return new CurrencyConversion(getCurrency(), (double) rate.getValue(), LocalDateTime.now(), getCurrency().getConversions().size());
    }

    public Currency getCurrency() {
        return (Currency) currencyJComboBox.getSelectedItem();
    }

    public void setCurrency(Currency currency) {
        this.currencyJComboBox.setSelectedItem(currency);
    }
}

