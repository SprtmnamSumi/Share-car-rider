package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.validation.FieldConversionUtils;
import cz.muni.fi.pv168.project.ui.model.validation.ValidatedInputField;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class TemplateDialog extends EntityDialog<Template> {
    private final ValidatedInputField titleField = new ValidatedInputField(){
        @Override
        public boolean evaluate(){
            return this.getText().length()>=2;
        }
    };
    private final JTextField descriptionField = new JTextField();

    private final JComboBox<Currency> currencyJComboBox;
    private final CurrencyConverter currencyConverter;
    private final JComboBox<Template> templateComboBoxModel;
    private final JComboBox<Category> categoryJComboBox;

    private final ValidatedInputField distanceField = getDoubleField();
    private final ValidatedInputField fuelConsumption = getDoubleField();
    private final ValidatedInputField costOfFuel = getDoubleField();
    private final ValidatedInputField numberOfPassengers = new ValidatedInputField();
    private final ValidatedInputField commission = getDoubleField();
    private final JCheckBox isChecked = new JCheckBox();
    private final Template template;
    private final KeyListener validationListener = new TypeListener();

    public TemplateDialog(Template template, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, CurrencyConverter currencyConverter) {
        this.template = template;

        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        this.currencyConverter = currencyConverter;
        addFields();
        setValues();
        setListeners();
        toggleOk();

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var ntemplate = (Template) e.getItem();
                this.template.setTitle(ntemplate.getTitle());
                this.template.setDescription(ntemplate.getDescription());
                this.template.setDistance(ntemplate.getDistance());
                this.template.setFuelConsumption(ntemplate.getFuelConsumption());
                this.template.setCostOfFuelPerLitre(ntemplate.getCostOfFuelPerLitreInDollars());
                this.template.setNumberOfPassengers(ntemplate.getNumberOfPassengers());
                this.template.setCommission(ntemplate.getCommission());
                this.template.setCategory(ntemplate.getCategory());
                setValues();
            }
        });

    }

    private void setListeners(){
        titleField.addKeyListener(validationListener);
        distanceField.addKeyListener(validationListener);
        fuelConsumption.addKeyListener(validationListener);
        costOfFuel.addKeyListener(validationListener);
        numberOfPassengers.addKeyListener(validationListener);
        commission.addKeyListener(validationListener);
    }

    private void setValues() {
        titleField.setText(template.getTitle());
        descriptionField.setText(template.getDescription());
        distanceField.setText(template.getDistance().toString());
        fuelConsumption.setText(String.valueOf(template.getFuelConsumption()));
        costOfFuel.setText(String.valueOf(template.getCostOfFuelPerLitreInDollars()));
        numberOfPassengers.setText(String.valueOf(template.getNumberOfPassengers()));
        commission.setText(String.valueOf(template.getCommission()));
        categoryJComboBox.setSelectedItem(template.getCategory());
    }

    private void addFields() {
        add("Template", templateComboBoxModel);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsumption);
        add("Cost of Fuel (1l)", costOfFuel);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Category", categoryJComboBox);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    Template getEntity() {
        template.setTitle(titleField.getText());
        template.setDescription(descriptionField.getText());
        template.setDistance(Double.parseDouble(distanceField.getText()));
        template.setFuelConsumption(Double.parseDouble(fuelConsumption.getText()));
        template.setCostOfFuelPerLitre(Double.parseDouble(costOfFuel.getText()));
        template.setNumberOfPassengers(Integer.parseInt(numberOfPassengers.getText()));
        template.setCommission(Double.parseDouble(commission.getText()));
        template.setCategory((Category) categoryJComboBox.getSelectedItem());
        return template;
    }

    private ValidatedInputField getDoubleField() {
        return new ValidatedInputField() {
            @Override
            public boolean evaluate() {
                return FieldConversionUtils.validateDouble(this);
            }
        };
    }

    private void toggleOk(){
        TemplateDialog.super.toggleOk(
                List.of(titleField, distanceField, fuelConsumption, costOfFuel, numberOfPassengers, commission)
                        .parallelStream().allMatch(ValidatedInputField::evaluate));
    }

    private class TypeListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            toggleOk();
        }
    }
}
