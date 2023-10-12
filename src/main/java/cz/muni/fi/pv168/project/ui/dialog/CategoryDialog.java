package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.entities.Category;

import javax.swing.*;

public final class CategoryDialog extends EntityDialog<Category> {

    private final JTextField nameField = new JTextField();
    private final JTextField colorField = new JTextField();

    private final Category category;

    public CategoryDialog(Category category) {
        this.category = category;
        setValues();
        addFields();
    }

    private void setValues() {

    }

    private void addFields() {
        add("Name", nameField);
        add("Color", colorField);
    }

    @Override
    Category getEntity() {
//        category.setName(nam.getText());
//        carRide.setFirstName(titleField.getText());
//        carRide.setLastName(descriptionField.getText());
//        carRide.setGender((Gender) genderModel.getSelectedItem());
//        carRide.setDepartment((Department) departmentModel.getSelectedItem());
//        carRide.setBirthDate(dateField.getValue());
        return category;
    }
}
