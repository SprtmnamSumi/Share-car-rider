package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;

import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.Color;
import java.util.Arrays;

final class CategoryDialog extends EntityDialog<Category> {

    private final ValidatedInputField nameField = new ValidatedInputField(ValidatorFactory.stringValidator(2, 150));
    private final JColorChooser colorChooser = new JColorChooser();
    private final Category category;

    CategoryDialog(Category category) {
        colorChooser.setColor(new Color(category.getColour()));
        this.category = category;
        setValues();
        addFields();
        ValidableListener validableListener = new ValidableListener() {
            @Override
            protected void onChange(boolean isValid) {
                CategoryDialog.super.toggleOk(isValid);
            }
        };
        validableListener.setListeners(nameField);
        validableListener.fireChange();
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

    private AbstractColorChooserPanel getBasicColorPicker() {
        return Arrays.stream(colorChooser.getChooserPanels()).toList().get(0);
    }
}
