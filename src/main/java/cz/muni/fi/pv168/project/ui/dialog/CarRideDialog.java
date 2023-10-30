package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.DateBar;

import javax.swing.*;

public final class CarRideDialog extends EntityDialog<CarRide> {
    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final JTextField templateField = new JTextField();
    private final ComboBoxModel<Currency> currencyModel = new DefaultComboBoxModel<>(Currency.values());
    private final JComboBox<Template> templateComboBoxModel;
    private final JComboBox<Category> categoryJComboBox;
    private final JSpinner rateField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commission = new JSpinner(new SpinnerNumberModel());
    private final JCheckBox isChecked = new JCheckBox();
    private final DateBar dateBar = new DateBar();
    private final CarRide carRide;

    public CarRideDialog(CarRide carRide, ListModel<Category> categoryModel, ListModel<Template> templateModel) {
        this.carRide = carRide;

        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        setValues();
        addFields();
    }

    private void setValues() {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());
        distanceField.setValue(carRide.getDistance());
        fuelConsumption.setValue(carRide.getFuelConsumption());
        costOfFuel.setValue(carRide.getCostOfFuelPerLitre());
        numberOfPassengers.setValue(carRide.getNumberOfPassengers());
        commission.setValue(carRide.getCommission());
        categoryJComboBox.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
    }

    private String getSpinnerValue(JSpinner spinner) {
        try {
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
        }

        return spinner.getValue().toString();
    }

    private void addFields() {
        add("Template", templateComboBoxModel);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsumption);
        add("Cost of Fuel (1l)", costOfFuel);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Date", dateBar);
        add("Category", categoryJComboBox);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    CarRide getEntity() {
        carRide.setTitle(titleField.getText());
        carRide.setDescription(descriptionField.getText());
        carRide.setDistance(Double.parseDouble(getSpinnerValue(distanceField)));
        carRide.setFuelConsumption(Double.parseDouble(getSpinnerValue(fuelConsumption)));
        carRide.setCostOfFuelPerLitre(Double.parseDouble(getSpinnerValue(costOfFuel)));
        carRide.setNumberOfPassengers(Integer.parseInt(getSpinnerValue(numberOfPassengers)));
        carRide.setCommission(Double.parseDouble(getSpinnerValue(costOfFuel)));
        carRide.setCategory((Category) categoryJComboBox.getSelectedItem());
        carRide.setDate(dateBar.getDate());
        return carRide;
    }
}
