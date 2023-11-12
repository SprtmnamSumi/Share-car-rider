package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidationListener;
import cz.muni.fi.pv168.project.ui.validation.Validator;
import cz.muni.fi.pv168.project.ui.validation.ValidatorFactory;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Arrays;

final class CategoryDialog extends EntityDialog<Category> {

    private final ValidatedInputField nameField = new ValidatedInputField(ValidatorFactory.titleValidator());
    private final JColorChooser colorChooser = new JColorChooser();
    private final Category category;
    private final ValidationListener validationListener = new ValidationListener() {
        @Override
        protected void onChange(boolean isValid) {
            CategoryDialog.super.toggleOk(isValid);
        }
    };

    CategoryDialog(Category category) {
        colorChooser.setColor(new Color(category.getColour()));
        this.category = category;
        setValues();
        addFields();
        validationListener.setListeners(nameField);
        validationListener.fireChange();
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
