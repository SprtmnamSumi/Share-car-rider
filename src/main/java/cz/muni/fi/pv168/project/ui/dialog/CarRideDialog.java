package cz.muni.fi.pv168.project.ui.dialog;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CategoryBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CostBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.DateBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TemplateBar;
import cz.muni.fi.pv168.project.ui.validation.FieldConversionUtils;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;
import cz.muni.fi.pv168.project.ui.validation.ValidationListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static javax.swing.JOptionPane.OK_OPTION;

public final class CarRideDialog extends EntityDialog<CarRide> {
    private final ValidatedInputField titleField = new ValidatedInputField() {
        @Override
        public boolean evaluate() {
            return this.getText().length() >= 2;
        }
    };
    private final JTextField descriptionField = new JTextField();
    private final JComboBox<Currency> currencyJComboBox;
    private final JComboBox<Template> templateComboBoxModel;
    private final CurrencyConverter currencyConverter;
    private final CategoryBar categoryBar;
    private final ValidatedInputField distanceField = getDoubleField();
    private final ValidatedInputField fuelConsumption = getDoubleField();

    private final ValidatedInputField numberOfPassengers = new ValidatedInputField();
    private final ValidatedInputField commission = getDoubleField();
    private final JCheckBox isChecked = new JCheckBox();
    private final DateBar dateBar = new DateBar();
    private final TemplateBar templateBar;
    private final JButton saveAsTemplate = new JButton("Save as template");
    private final CostBar costBar;


    private final TableModel<Template> entityCrudService;
    private final ValidationListener validationListener;
    CarRide carRide;


    public CarRideDialog(CarRide carRide, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, TableModel<Template> entityCrudService, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableModel, CurrencyConverter currencyConverter) {
        this.carRide = carRide;
        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        this.currencyConverter = currencyConverter;
        categoryBar = new CategoryBar(categoryModel, categoryActionFactory, categoryTableModel);
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));

        templateBar = new TemplateBar(templateComboBoxModel, new JButton(""));

        this.costBar = new CostBar(currencyModel, currencyConverter);

        setValues(carRide);
        addFields();

        this.entityCrudService = entityCrudService;
        validationListener = new ValidationListener(distanceField, fuelConsumption, numberOfPassengers, commission, costBar) {
            @Override
            protected void onChange(boolean isValid) {
                CarRideDialog.super.toggleOk(isValid);
            }
        };
        validationListener.fireChange();

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var template = (Template) e.getItem();
                var templateCarRide = new CarRide(null, template.getTitle(), template.getDescription(), template.getDistance(), template.getFuelConsumption(), template.getCostOfFuelPerLitreInDollars(), template.getNumberOfPassengers(), template.getCommission(), LocalDateTime.now(), template.getCategory(), template.getCurrency(), template.getConversionToDollars());
                setValues(templateCarRide);
            }
        });
    }


    private void setValues(CarRide carRide) {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());
        distanceField.setText(String.valueOf(carRide.getDistance()));
        fuelConsumption.setText(String.valueOf(carRide.getFuelConsumption()));
        numberOfPassengers.setText(String.valueOf(carRide.getNumberOfPassengers()));
        categoryBar.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
        commission.setText(String.valueOf(carRide.getCommission()));
        currencyJComboBox.setSelectedItem(carRide.getCurrency());
        costBar.SetValues(carRide.getCostOfFuelPerLitreInDollars(), carRide.getConversionToDollars(), carRide.getCurrency());
    }


    private String getSpinnerValue(JSpinner spinner) {
        try {
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
        }

        return spinner.getValue().toString();
    }

    private void addFields() {
        add("Template", templateComboBoxModel);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsumption);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Date", dateBar);
        add("Category", categoryBar);
        add("Currency", currencyJComboBox);
        add("Count me in the calculation of per price person", isChecked);
        add("Cost of Fuel", costBar);
    }

    @Override
    CarRide getEntity() {
        carRide.setTitle(titleField.getText());
        carRide.setDescription(descriptionField.getText());
        carRide.setDistance(Double.parseDouble(distanceField.getText()));
        carRide.setFuelConsumption(Double.parseDouble(fuelConsumption.getText()));
        carRide.setNumberOfPassengers(Integer.parseInt(numberOfPassengers.getText()));
        carRide.setCommission(Double.parseDouble(commission.getText()));
        carRide.setCategory(categoryBar.getSelectedItem());
        carRide.setDate(dateBar.getDate());
        carRide.setCurrency((Currency) currencyJComboBox.getSelectedItem());

        carRide.setCurrency(costBar.getCurrency());
        carRide.setConversionRateToDollar(costBar.getConversionRateToDollars());
        carRide.setCostOfFuelPerLitre(costBar.getCostOfFuelInDollars());

        return carRide;
    }

    Template getAsTemplate() {
        var ride = getEntity();
        Template template = new Template(UUID.randomUUID().toString(), ride.getTitle(), ride.getDescription(), ride.getDistance(), ride.getFuelConsumption(), ride.getCostOfFuelPerLitreInDollars(), ride.getNumberOfPassengers(), ride.getCommission(), ride.getCategory(), ride.getCurrency(), ride.getConversionToDollars());
        return template;
    }

    private void addTemplate(Template templateToBeAdded) {
        entityCrudService.addRow(templateToBeAdded);
    }

    @Override
    public Optional<CarRide> show(JComponent parentComponent, String title) {
        boolean isOk = isconfirmed(parentComponent, title);

        if (!isOk) {
            return Optional.empty();
        }

        if (entityCrudService.getAllEntities().stream().anyMatch(template -> template.equals(getAsTemplate()))) {
            return Optional.of(getEntity());
        }

        int res = JOptionPane.showConfirmDialog(null, "Do you want to save this as template?", "Save as a template?", JOptionPane.YES_NO_OPTION);
        if (res == OK_OPTION) {
            addTemplate(getAsTemplate());
        }
        return Optional.of(getEntity());
    }

    private ValidatedInputField getDoubleField() {
        return new ValidatedInputField() {
            @Override
            public boolean evaluate() {
                return FieldConversionUtils.validateDouble(this)
                        && Double.parseDouble(this.getText()) >= 0.0f;
            }
        };
    }
}
