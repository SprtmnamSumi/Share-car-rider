package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Arrays;

public final class CategoryDialog extends EntityDialog<Category> {

    private final JTextField nameField = new JTextField();
    private final JColorChooser colorChooser = new JColorChooser(Color.WHITE);

    private final Category category;

    public CategoryDialog(Category category, ListModel<Category> categoriestListModel) {
        this.category = category;
        setValues();
        addFields();
    }

    @Override
    Category getEntity() {
        category.setName(nameField.getText());
        category.setColour(colorChooser.getColor().getRGB());
        return category;
    }

    private void setValues() {
        nameField.setText(category.getName());
    }

    private void addFields() {
        add("Name", nameField);
        add("Color", getBasicColorPicker());
    }

    private AbstractColorChooserPanel getBasicColorPicker(){
        return Arrays.stream(colorChooser.getChooserPanels()).toList().get(0);
    }
}
