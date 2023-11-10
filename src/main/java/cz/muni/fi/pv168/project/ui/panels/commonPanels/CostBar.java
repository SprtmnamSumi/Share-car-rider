package cz.muni.fi.pv168.project.ui.panels.commonPanels;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;


public class CostBar extends JPanel {
    private final String[] currencyModel = {"USD", "EUR", "GBP"};
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JComboBox<Currency> currencyJComboBox;

    public CostBar(ListModel<Currency> currencyModel, double costOfFuelval, double covertRateval, Currency currency) {
        super(new FlowLayout(FlowLayout.CENTER));

        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        currencyJComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
//                var template = (Template) e.getItem();
//                var templateCarRide = new CarRide(null, template.getTitle(), template.getDescription(), template.getDistance(), template.getFuelConsumption(), template.getCostOfFuelPerLitreInDollars(), template.getNumberOfPassengers(), template.getCommission(), LocalDateTime.now(), template.getCategory(), currencyModel.getElementAt(0));
////                setValues(templateCarRide);
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
        JSpinner rate = new JSpinner(new SpinnerNumberModel());
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
        this.costOfFuel.setValue(covertRate);
    }

    public Currency getCurrency() {
        return (Currency) currencyJComboBox.getSelectedItem();
    }

    public void setCurrency(Currency currency) {
        this.currencyJComboBox.setSelectedItem(currency);
    }
    
}
