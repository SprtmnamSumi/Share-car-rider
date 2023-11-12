package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CategoryBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CostBar;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidationListener;
import cz.muni.fi.pv168.project.ui.validation.ValidatorFactory;

import javax.swing.*;
import java.awt.event.ItemEvent;

class TemplateDialog extends EntityDialog<Template> {
    private final ValidatedInputField titleField = new ValidatedInputField(ValidatorFactory.titleValidator()) {
        @Override
        public boolean evaluate() {
            return this.getText().length() >= 2;
        }
    };
    private final JTextField descriptionField = new JTextField();

    private final JComboBox<Currency> currencyJComboBox;
    private final CurrencyConverter currencyConverter;
    private final JComboBox<Template> templateComboBoxModel;
    //    private final JComboBox<Category> categoryJComboBox;
    private final CategoryBar categoryBar;

    private final ValidatedInputField distanceField = new ValidatedInputField(ValidatorFactory.doubleValidator());
    private final ValidatedInputField fuelConsumption = new ValidatedInputField(ValidatorFactory.doubleValidator());

    private final ValidatedInputField numberOfPassengers = new ValidatedInputField(ValidatorFactory.intValidator());
    private final ValidatedInputField commission = new ValidatedInputField(ValidatorFactory.doubleValidator());
    private final JCheckBox isChecked = new JCheckBox();
    private final Template template;
    private final CostBar costBar;

    private final ValidationListener validationListener;

    TemplateDialog(Template template, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, CurrencyConverter currencyConverter, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableModel) {
        validationListener = new ValidationListener() {
            @Override
            protected void onChange(boolean isValid) {
                TemplateDialog.super.toggleOk(isValid);
            }
        };

        this.template = template;
        categoryBar = new CategoryBar(categoryModel, categoryActionFactory, categoryTableModel, validationListener);
        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        this.currencyConverter = currencyConverter;
        this.costBar = new CostBar(currencyModel, currencyConverter, validationListener);
        this.validationListener.setListeners(titleField, distanceField, fuelConsumption, numberOfPassengers, commission, costBar, costBar);
        addFields();
        setValues();
        validationListener.fireChange();

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

    private void setValues() {
        titleField.setText(template.getTitle());
        descriptionField.setText(template.getDescription());
        distanceField.setText(template.getDistance().toString());
        fuelConsumption.setText(String.valueOf(template.getFuelConsumption()));
        costBar.SetValues(template.getCostOfFuelPerLitreInDollars(), template.getConversionToDollars(), template.getCurrency());
        numberOfPassengers.setText(String.valueOf(template.getNumberOfPassengers()));
        commission.setText(String.valueOf(template.getCommission()));
        categoryBar.setSelectedItem(template.getCategory());
    }

    private void addFields() {
        add("Template", templateComboBoxModel);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsumption);
        add("Cost of Fuel (1l)", costBar);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Category", categoryBar);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    Template getEntity() {
        template.setTitle(titleField.getText());
        template.setDescription(descriptionField.getText());
        template.setDistance(Double.parseDouble(distanceField.getText()));
        template.setFuelConsumption(Double.parseDouble(fuelConsumption.getText()));
        template.setCostOfFuelPerLitre(costBar.getCostOfFuelInDollars());
        template.setCurrency(costBar.getCurrency());
        template.setConversionRateToDollar(costBar.getConversionRateToDollars());
        template.setNumberOfPassengers(Integer.parseInt(numberOfPassengers.getText()));
        template.setCommission(Double.parseDouble(commission.getText()));
        template.setCategory(categoryBar.getSelectedItem());
        return template;
    }
}
