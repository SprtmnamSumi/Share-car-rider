package cz.muni.fi.pv168.project.ui.dialog;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import cz.muni.fi.pv168.project.business.service.validation.common.ValidatorFactory;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CategoryBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CostBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.DateBar;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.TemplateBar;
import cz.muni.fi.pv168.project.ui.validation.ValidableListener;
import cz.muni.fi.pv168.project.ui.validation.ValidatedInputField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListModel;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.util.UUID;

final class CarRideDialog extends EntityDialog<CarRide> {
    private final ValidatedInputField titleField = new ValidatedInputField(ValidatorFactory.stringValidator(2, 150));
    private final ValidatedInputField descriptionField = new ValidatedInputField((t) -> ValidationResult.success());
    private final JComboBox<Template> templateComboBoxModel;
    private final CategoryBar categoryBar;
    private final ValidatedInputField distanceField = new ValidatedInputField(ValidatorFactory.doubleValidator());
    private final ValidatedInputField fuelConsumption = new ValidatedInputField(ValidatorFactory.doubleValidator());
    private final ValidatedInputField numberOfPassengers = new ValidatedInputField(ValidatorFactory.intValidator());
    private final ValidatedInputField commission = new ValidatedInputField(ValidatorFactory.doubleValidator());
    private final DateBar dateBar = new DateBar();
    private final TemplateBar templateBar;
    private final JButton saveAsTemplate = new JButton("Save as template");
    private final CostBar costBar;
    private final TableModel<Template> entityCrudService;
    private final ValidableListener validableListener;
    private final CarRide carRide;

    CarRideDialog(CarRide carRide, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, TableModel<Template> entityCrudService, DefaultActionFactory<Category> categoryActionFactory, TableModel<Category> categoryTableModel, CurrencyConverter currencyConverter) {
        this.carRide = carRide;
        this.entityCrudService = entityCrudService;

        validableListener = new ValidableListener() {
            @Override
            protected void onChange(boolean isValid) {
                CarRideDialog.super.toggleOk(isValid);
                if (isValid)
                    saveAsTemplate.setEnabled(entityCrudService.getAllEntities().stream().noneMatch(template -> template.equals(getAsTemplate())));
            }
        };


        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        categoryBar = new CategoryBar(categoryModel, categoryActionFactory, categoryTableModel, validableListener);
        templateBar = new TemplateBar(templateComboBoxModel, saveAsTemplate);
        this.costBar = new CostBar(currencyModel, currencyConverter, validableListener);
        validableListener.setListeners(titleField, descriptionField, distanceField, fuelConsumption, numberOfPassengers, commission, costBar, categoryBar);

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var template = (Template) e.getItem();
                var templateCarRide = new CarRide(null, template.getTitle(), template.getDescription(), template.getDistance(), template.getFuelConsumption(), template.getCostOfFuelPerLitreInDollars(), template.getNumberOfPassengers(), template.getCommission(), template.getCategory(), template.getCurrency(), template.getConversionToDollars(), LocalDateTime.now());
                setValues(templateCarRide);
            }
        });

        saveAsTemplate.addActionListener(e -> {
            addTemplate(getAsTemplate());
            templateComboBoxModel.setSelectedItem(getAsTemplate());
            validableListener.fireChange();
        });

        setValues(carRide);
        addFields();
    }

    public void setValues(CarRide carRide) {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());
        distanceField.setText(String.valueOf(carRide.getDistance()));
        fuelConsumption.setText(String.valueOf(carRide.getFuelConsumption()));
        numberOfPassengers.setText(String.valueOf(carRide.getNumberOfPassengers()));
        categoryBar.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
        commission.setText(String.valueOf(carRide.getCommission()));
        costBar.SetValues(carRide.getCostOfFuelPerLitreInDollars(), carRide.getConversionToDollars(), carRide.getCurrency());
        validableListener.fireChange();
    }

    private void addFields() {
        add("Template", templateBar);
        add("Title", titleField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsumption);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Category", categoryBar);
        add("Cost of Fuel", costBar);
        add("Date", dateBar);
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

        carRide.setCurrency(costBar.getCurrency());
        carRide.setConversionRateToDollar(costBar.getConversionRateToDollars());
        carRide.setCostOfFuelPerLitre(costBar.getCostOfFuelInDollars());

        return carRide;
    }

    Template getAsTemplate() {
        var ride = getEntity();
        return new Template(UUID.randomUUID().toString(),
                ride.getTitle(), ride.getDescription(),
                ride.getDistance(), ride.getFuelConsumption(),
                ride.getCostOfFuelPerLitreInDollars(), ride.getNumberOfPassengers(),
                ride.getCommission(), ride.getCategory(),
                ride.getCurrency(), ride.getConversionToDollars());
    }

    private void addTemplate(Template templateToBeAdded) {
        entityCrudService.addRow(templateToBeAdded);
    }
}

