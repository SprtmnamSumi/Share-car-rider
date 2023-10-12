package cz.muni.fi.pv168.employees.ui.dialog;

import cz.muni.fi.pv168.employees.model.CarRide;
import cz.muni.fi.pv168.employees.model.Category;
import cz.muni.fi.pv168.employees.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.employees.ui.model.LocalDateModel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.time.LocalDate;

public final class RideDialog extends EntityDialog<CarRide> {

    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();


    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsiumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commision = new JSpinner(new SpinnerNumberModel());

    private final ComboBoxModel<Category> departmentModel;
    private final DateModel<LocalDate> dateField = new LocalDateModel();

    private final CarRide carRide;

    public RideDialog(CarRide carRide, ListModel<Category> departmentModel) {
        this.carRide = carRide;
        this.departmentModel = new ComboBoxModelAdapter<>(departmentModel);
        setValues();
        addFields();
    }

    private void setValues() {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());

        departmentModel.setSelectedItem(carRide.getCategory());
//        dateField.setValue(carRide.getDate());
    }

    private void addFields() {
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", new JDatePicker(dateField));
        add("Cost of Fuel (per 1 l:", new JComboBox<>(departmentModel));
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
