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
import cz.muni.fi.pv168.project.ui.panels.commonPanels.DateBar;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static javax.swing.JOptionPane.*;

public final class CarRideDialog extends EntityDialog<CarRide> {
    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final JComboBox<Currency> currencyJComboBox;
    private final JComboBox<Template> templateComboBoxModel;
    private final CurrencyConverter currencyConverter;
    private final CategoryBar categoryBar;
    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commission = new JSpinner(new SpinnerNumberModel());
    private final JCheckBox isChecked = new JCheckBox();
    private final DateBar dateBar = new DateBar();


    private final TableModel<Template> entityCrudService;
    CarRide carRide;

    public CarRideDialog(CarRide carRide, ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, TableModel<Template> entityCrudService, DefaultActionFactory<Category> categoryActionFactory, CategoryTableModel categoryTableModel, CurrencyConverter currencyConverter) {
        this.carRide = carRide;
        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        this.currencyConverter = currencyConverter;
        categoryBar = new CategoryBar(categoryModel, categoryActionFactory, categoryTableModel);
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        setValues(carRide);
        addFields();

        this.entityCrudService = entityCrudService;

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var template = (Template) e.getItem();
                var templateCarRide = new CarRide(null, template.getTitle(), template.getDescription(), template.getDistance(), template.getFuelConsumption(), template.getCostOfFuelPerLitreInDollars(), template.getNumberOfPassengers(), template.getCommission(), LocalDateTime.now(), template.getCategory(), currencyModel.getElementAt(0));
                setValues(templateCarRide);
            }
        });
    }


    private void setValues(CarRide carRide) {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());
        distanceField.setValue(carRide.getDistance());
        fuelConsumption.setValue(carRide.getFuelConsumption());
        numberOfPassengers.setValue(carRide.getNumberOfPassengers());
        categoryBar.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
        commission.setValue(carRide.getCommission());
        currencyJComboBox.setSelectedItem(carRide.getCurrency());
        double costOfFuelPerLitre = currencyConverter.convertFromDolarsToCurrency(carRide.getCurrency(), carRide.getCostOfFuelPerLitreInDollars());
        costOfFuel.setValue(costOfFuelPerLitre);
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
        add("Cost of Fuel (1l)", costOfFuel);
        add("Number of Passengers", numberOfPassengers);
        add("Commission (%)", commission);
        add("Date", dateBar);
        add("Category", categoryBar);
        add("Currency", currencyJComboBox);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    CarRide getEntity() {
        carRide.setTitle(titleField.getText());
        carRide.setDescription(descriptionField.getText());
        carRide.setDistance(Double.parseDouble(getSpinnerValue(distanceField)));
        carRide.setFuelConsumption(Double.parseDouble(getSpinnerValue(fuelConsumption)));
        carRide.setNumberOfPassengers(Integer.parseInt(getSpinnerValue(numberOfPassengers)));
        carRide.setCommission(Double.parseDouble(getSpinnerValue(commission)));
        carRide.setCategory(categoryBar.getSelectedItem());
        carRide.setDate(dateBar.getDate());
        carRide.setCurrency((Currency) currencyJComboBox.getSelectedItem());

        var costInDefCurrency = Double.parseDouble(getSpinnerValue(costOfFuel));
        var costInDollars = currencyConverter.convertFromCurrencyTOdollars(carRide.getCurrency(), costInDefCurrency);
        carRide.setCostOfFuelPerLitre(costInDollars);
        return carRide;
    }

    Template getAsTemplate() {
        var ride = getEntity();
        Template template = new Template(UUID.randomUUID().toString(), ride.getTitle(), ride.getDescription(), ride.getDistance(), ride.getFuelConsumption(), ride.getCostOfFuelPerLitreInDollars(), ride.getNumberOfPassengers(), ride.getCommission(), ride.getCategory(), currencyJComboBox.getItemAt(0));
        return template;
    }

    private void addTemplate(Template templateToBeAdded) {
        entityCrudService.addRow(templateToBeAdded);
    }

    @Override
    public Optional<CarRide> show(JComponent parentComponent, String title) {
        int result = JOptionPane.showOptionDialog(parentComponent, panel, title,
                OK_CANCEL_OPTION, PLAIN_MESSAGE, null, null, null);
        if (result != OK_OPTION) {
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
}
