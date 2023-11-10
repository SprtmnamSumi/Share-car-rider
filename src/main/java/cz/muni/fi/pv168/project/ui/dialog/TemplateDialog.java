package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class TemplateDialog extends EntityDialog<Template> {
    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();

    private final JComboBox<Currency> currencyJComboBox;
    private final CurrencyConverter currencyConverter;
    private final JComboBox<Template> templateComboBoxModel;
    private final JComboBox<Category> categoryJComboBox;

    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commission = new JSpinner(new SpinnerNumberModel());
    private final JCheckBox isChecked = new JCheckBox();
    private final Template template;

    public TemplateDialog(Template template, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, CurrencyConverter currencyConverter) {
        this.template = template;

        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        this.currencyConverter = currencyConverter;
        setValues();
        addFields();

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var ntemplate = (Template) e.getItem();
                this.template.setTitle(ntemplate.getTitle());
                this.template.setDescription(ntemplate.getDescription());
                this.template.setDistance(ntemplate.getDistance());
                this.template.setFuelConsumption(ntemplate.getFuelConsumption());
                this.template.setCostOfFuelPerLitre(ntemplate.getCostOfFuelPerLitre());
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
        distanceField.setValue(template.getDistance());
        fuelConsumption.setValue(template.getFuelConsumption());
        costOfFuel.setValue(template.getCostOfFuelPerLitre());
        numberOfPassengers.setValue(template.getNumberOfPassengers());
        commission.setValue(template.getCommission());
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

    private String getSpinnerValue(JSpinner spinner) {
        try {
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
        }

        return spinner.getValue().toString();
    }

    @Override
    Template getEntity() {
        template.setTitle(titleField.getText());
        template.setDescription(descriptionField.getText());
        template.setDistance(Double.parseDouble(getSpinnerValue(distanceField)));
        template.setFuelConsumption(Double.parseDouble(getSpinnerValue(fuelConsumption)));
        template.setCostOfFuelPerLitre(Double.parseDouble(getSpinnerValue(costOfFuel)));
        template.setNumberOfPassengers(Integer.parseInt(getSpinnerValue(numberOfPassengers)));
        template.setCommission(Double.parseDouble(getSpinnerValue(costOfFuel)));
        template.setCategory((Category) categoryJComboBox.getSelectedItem());
        return template;
    }
}
