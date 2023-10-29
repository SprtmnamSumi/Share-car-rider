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
    private final JSpinner fuelConsiumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commision = new JSpinner(new SpinnerNumberModel());
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
        fuelConsiumption.setValue(carRide.getFuelConsumption());
        costOfFuel.setValue(carRide.getCostOfFuelPerLitre());
        numberOfPassengers.setValue(carRide.getNumberOfPassengers());
        commision.setValue(carRide.getCommission());
        categoryJComboBox.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
    }

    private <T> T getSpinnerValue(JSpinner spinner) {
        try {
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
        }
        T value = (T) spinner.getValue();
        return value;
    }

    private void addFields() {
        add("Template", templateComboBoxModel);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsiumption);
        add("Cost of Fuel (1l)", costOfFuel);
        add("Number of Passengers", numberOfPassengers);
        add("Commision (%)", commision);
        add("Date", dateBar);
        add("Category", categoryJComboBox);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    CarRide getEntity() {
        carRide.setTitle(titleField.getText());
        carRide.setDescription(descriptionField.getText());
//        carRide.setDistance(getSpinnerValue(distanceField));
//        fuelConsiumption.setValue(getSpinnerValue(fuelConsiumption));
//        carRide.setCostOfFuelPerLitre(getSpinnerValue(costOfFuel));
        carRide.setNumberOfPassengers(getSpinnerValue(numberOfPassengers));
//        carRide.setCommission(getSpinnerValue(commision));
        carRide.setCategory((Category) categoryJComboBox.getSelectedItem());
        carRide.setDate(dateBar.getDate());
        return carRide;
    }
}
