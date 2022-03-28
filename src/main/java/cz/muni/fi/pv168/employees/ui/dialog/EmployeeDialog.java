package cz.muni.fi.pv168.employees.ui.dialog;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.model.Gender;
import cz.muni.fi.pv168.employees.ui.model.ComboBoxModelAdapter;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListModel;

public final class EmployeeDialog extends EntityDialog<Employee> {

    private final JTextField firstNameField = new JTextField();
    private final JTextField lastNameField = new JTextField();
    private final ComboBoxModel<Gender> genderModel = new DefaultComboBoxModel<>(Gender.values());
    private final ComboBoxModel<Department> departmentModel;

    private final Employee employee;

    public EmployeeDialog(Employee employee, ListModel<Department> departmentModel) {
        this.employee = employee;
        this.departmentModel = new ComboBoxModelAdapter<>(departmentModel);
        setValues();
        addFields();
    }

    private void setValues() {
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        genderModel.setSelectedItem(employee.getGender());
        departmentModel.setSelectedItem(employee.getDepartment());
    }

    private void addFields() {
        add("First Name:", firstNameField);
        add("Last Name:", lastNameField);
        add("Gender:", new JComboBox<>(genderModel));
        add("Department:", new JComboBox<>(departmentModel));
    }

    @Override
    Employee getEntity() {
        employee.setFirstName(firstNameField.getText());
        employee.setLastName(lastNameField.getText());
        employee.setGender((Gender) genderModel.getSelectedItem());
        employee.setDepartment((Department) departmentModel.getSelectedItem());
        return employee;
    }
}
