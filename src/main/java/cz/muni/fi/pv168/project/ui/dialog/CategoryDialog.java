package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;

import javax.swing.*;

public final class CategoryDialog extends EntityDialog<Category> {

    private final JTextField nameField = new JTextField();
    private final JTextField colorField = new JTextField();

    private final Category category;

    public CategoryDialog(Category category, ListModel<Category> categoriestListModel) {
        this.category = category;
        setValues();
        addFields();
    }

    private void setValues() {
        nameField.setText(category.getName());
        colorField.setText(category.getColour());
    }

    private void addFields() {
        add("Name", nameField);
        add("Color", colorField);
    }

    @Override
    Category getEntity() {
        category.setName(nameField.getText());
        category.setColour(colorField.getText());
        return category;
    }
}
