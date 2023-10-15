package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.entities.Currency;
import cz.muni.fi.pv168.project.entities.Template;
import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.panels.commonPanels.CostBar;
import org.jdatepicker.DateModel;

import javax.swing.*;
import java.time.LocalDate;

public final class TemplateDialog extends EntityDialog<Template> {

    private final JTextField titleField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final JTextField templateField = new JTextField();
    private final ComboBoxModel<Currency> currencyModel = new DefaultComboBoxModel<>(Currency.values());
    private final ComboBoxModel<Template> categoryMmodel;
    private final JSpinner rateField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner distanceField = new JSpinner(new SpinnerNumberModel());
    private final JSpinner fuelConsiumption = new JSpinner(new SpinnerNumberModel());
    private final JSpinner costOfFuel = new JSpinner(new SpinnerNumberModel());
    private final JSpinner numberOfPassengers = new JSpinner(new SpinnerNumberModel());
    private final JSpinner commision = new JSpinner(new SpinnerNumberModel());

    private final ComboBoxModel<Category> categoryModel;
    private final DateModel<LocalDate> dateField = new LocalDateModel();

    private final Template template;

    public TemplateDialog(Template template, ListModel<Category> categoryModel, ListModel<Template> templateModel) {
        this.template = template;
        this.categoryModel = new ComboBoxModelAdapter<>(categoryModel);
        this.categoryMmodel = new ComboBoxModelAdapter<>((templateModel));
        setValues();
        addFields();
    }

    private void setValues() {
        titleField.setText(template.getTitle());
        descriptionField.setText(template.getDescription());

        categoryModel.setSelectedItem(template.getCategory());
//        dateField.setValue(template.getDate());
    }

    private void addFields() {
        add("Template", new JComboBox<>(categoryMmodel));
        add("Title", templateField);
        add("Description", descriptionField);
        add("Distance", distanceField);
        add("Average Fuel Consumption (per 100km)", fuelConsiumption);
        add("Cost of Fuel (1l)", new CostBar());
        add("Number of Passengers", numberOfPassengers);
        add("Commision (%)", commision);
        add("Category", new JComboBox<>(categoryModel));
        add("Count me in the calculation of per price person", new JCheckBox());
    }

    @Override
    Template getEntity() {
//        template.setFirstName(titleField.getText());
//        template.setLastName(descriptionField.getText());
//        template.setGender((Gender) genderModel.getSelectedItem());
//        template.setDepartment((Department) departmentModel.getSelectedItem());
//        template.setBirthDate(dateField.getValue());
        return template;
    }
}
