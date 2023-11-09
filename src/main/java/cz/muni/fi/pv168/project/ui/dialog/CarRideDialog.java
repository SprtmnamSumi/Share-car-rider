package cz.muni.fi.pv168.project.ui.dialog;


import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
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
    private final JTextField templateField = new JTextField();
    private final JComboBox<Currency> currencyJComboBox;
    private final JComboBox<Template> templateComboBoxModel;
    private final JComboBox<Category> categoryJComboBox;
    private final JSpinner rateField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commission = new JSpinner(new SpinnerNumberModel());
    private final JCheckBox isChecked = new JCheckBox();
    private final DateBar dateBar = new DateBar();


    private final TableModel<Template> entityCrudService;

    public CarRideDialog(ListModel<Category> categoryModel, ListModel<Currency> currencyModel, ListModel<Template> templateModel, TableModel<Template> entityCrudService) {
        var carRide = new CarRide(null, "", "", 0.0, 0, 0, 0, 0, LocalDateTime.now(), null);

        templateComboBoxModel = new JComboBox<>(new ComboBoxModelAdapter<>(templateModel));
        categoryJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(categoryModel));
        currencyJComboBox = new JComboBox<>(new ComboBoxModelAdapter<>(currencyModel));
        setValues(carRide);
        addFields();

        this.entityCrudService = entityCrudService;

        templateComboBoxModel.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                var template = (Template) e.getItem();
                var templateCarRide = new CarRide(null, template.getTitle(), template.getDescription(), template.getDistance(), template.getFuelConsumption(), template.getCostOfFuelPerLitre(), template.getNumberOfPassengers(), template.getCommission(), LocalDateTime.now(), template.getCategory());
                setValues(templateCarRide);
            }
        });
    }


    private void setValues(CarRide carRide) {
        titleField.setText(carRide.getTitle());
        descriptionField.setText(carRide.getDescription());
        distanceField.setValue(carRide.getDistance());
        fuelConsumption.setValue(carRide.getFuelConsumption());
        costOfFuel.setValue(carRide.getCostOfFuelPerLitre());
        numberOfPassengers.setValue(carRide.getNumberOfPassengers());
        commission.setValue(carRide.getCommission());
        categoryJComboBox.setSelectedItem(carRide.getCategory());
        dateBar.setDate(carRide.getDate());
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
        add("Category", categoryJComboBox);
        add("Count me in the calculation of per price person", isChecked);
    }

    @Override
    CarRide getEntity() {
        var carRide = new CarRide(null, "", "", 0.0, 0, 0, 0, 0, LocalDateTime.now(), null);
        carRide.setTitle(titleField.getText());
        carRide.setDescription(descriptionField.getText());
        carRide.setDistance(Double.parseDouble(getSpinnerValue(distanceField)));
        carRide.setFuelConsumption(Double.parseDouble(getSpinnerValue(fuelConsumption)));
        carRide.setCostOfFuelPerLitre(Double.parseDouble(getSpinnerValue(costOfFuel)));
        carRide.setNumberOfPassengers(Integer.parseInt(getSpinnerValue(numberOfPassengers)));
        carRide.setCommission(Double.parseDouble(getSpinnerValue(commission)));
        carRide.setCategory((Category) categoryJComboBox.getSelectedItem());
        carRide.setDate(dateBar.getDate());
        return carRide;
    }

    Template getAsTemplate() {
        var ride = getEntity();
        Template template = new Template(UUID.randomUUID().toString(), ride.getTitle(), ride.getDescription(), ride.getDistance(), ride.getFuelConsumption(), ride.getCostOfFuelPerLitre(), ride.getNumberOfPassengers(), ride.getCommission(), ride.getCategory());
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
