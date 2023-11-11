package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidationListener;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public final class CategoryDialog extends EntityDialog<Category> {

    private final ValidatedInputField nameField = new ValidatedInputField(){
        @Override
        public boolean evaluate(){
            return this.getText().length()>=2;
        }
    };
    private final JColorChooser colorChooser = new JColorChooser(Color.WHITE);
    private final Category category;
    private final ValidationListener validationListener = new ValidationListener(nameField) {
        @Override
        protected void onChange(boolean isValid) {
            CategoryDialog.super.toggleOk(isValid);
        }
    };

    public CategoryDialog(Category category, ListModel<Category> categoriestListModel) {
        this.category = category;
        setValues();
        addFields();
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
