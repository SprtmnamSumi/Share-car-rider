package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.entities.CarRideTemplate;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.entities.Currency;
import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CostBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.DateBar;
import org.jdatepicker.DateModel;

import javax.swing.*;
import java.time.LocalDate;

public final class RideDialog extends EntityDialog<CarRide> {

    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final JTextField templateField = new JTextField();
    private final ComboBoxModel<Currency> currencyModel = new DefaultComboBoxModel<>(Currency.values());
    private final ComboBoxModel<CarRideTemplate> categoryMmodel;
    private final JSpinner rateField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsiumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commision = new JSpinner(new SpinnerNumberModel());

    private final ComboBoxModel<Category> categoryModel;
    private final DateModel<LocalDate> dateField = new LocalDateModel();

    private final CarRide carRide;

    public RideDialog(CarRide carRide, ListModel<Category> categoryModel, ListModel<CarRideTemplate> templateModel) {
        this.carRide = carRide;
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.categoryMmodel = new ComboBoxModelAdapter<>((templateModel));
        setValues();
        addFields();
    }

    private void setValues() {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());

        categoryModel.setSelectedItem(carRide.getCategory());
//        dateField.setValue(carRide.getDate());
    }

    private void addFields() {
        add("Template", new JComboBox<>(categoryMmodel));
        add("Title", templateField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsiumption);
        add("Cost of Fuel (1l)", new CostBar());
        add("Number of Passengers", numberOfPassengers);
        add("Commision (%)", commision);
//        add("Date", new JDatePicker(dateField));
        add("Date", new DateBar(dateField));
        add("Category", new JComboBox<>(categoryModel));
        add("Count me in the calculation of per price person", new JCheckBox());
    }

    @Override
    CarRide getEntity() {
//        carRide.setFirstName(titleField.getText());
//        carRide.setLastName(descriptionField.getText());
//        carRide.setGender((Gender) genderModel.getSelectedItem());
//        carRide.setDepartment((Department) departmentModel.getSelectedItem());
//        carRide.setBirthDate(dateField.getValue());
        return carRide;
    }
}
