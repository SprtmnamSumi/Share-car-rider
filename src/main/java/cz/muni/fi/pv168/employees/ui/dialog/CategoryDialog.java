package cz.muni.fi.pv168.employees.ui.dialog;

import cz.muni.fi.pv168.employees.model.CarRide;
import cz.muni.fi.pv168.employees.model.Category;
import org.jdatepicker.JDatePicker;

import javax.swing.*;

public final class CategoryDialog extends EntityDialog<Category> {



    private final Category category;

    public CategoryDialog(Category category) {
        this.category = category;
        setValues();
        addFields();
    }

    private void setValues() {

    }

    private void addFields() {

    }

    @Override
    Category getEntity() {
//        carRide.setFirstName(titleField.getText());
//        carRide.setLastName(descriptionField.getText());
//        carRide.setGender((Gender) genderModel.getSelectedItem());
//        carRide.setDepartment((Department) departmentModel.getSelectedItem());
//        carRide.setBirthDate(dateField.getValue());
        return category;
    }
}
