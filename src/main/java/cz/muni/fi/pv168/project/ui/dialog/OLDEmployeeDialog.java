//package cz.muni.fi.pv168.project.ui.dialog;
//
//import cz.muni.fi.pv168.project.entities.old.Department;
//import cz.muni.fi.pv168.project.entities.old.Employee;
//import cz.muni.fi.pv168.project.entities.old.Gender;
//import cz.muni.fi.pv168.project.ui.model.adapters.ComboBoxModelAdapter;
//import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
//import cz.muni.fi.pv168.project.ui.renderers.OLD.DepartmentRenderer;
//import org.jdatepicker.DateModel;
//import org.jdatepicker.JDatePicker;
//
//import javax.swing.*;
//import java.time.LocalDate;
//
//public final class OLDEmployeeDialog extends EntityDialog<Employee> {
//
//    private final JTextField firstNameField = new JTextField();
//    private final JTextField lastNameField = new JTextField();
//    private final ComboBoxModel<Gender> genderModel = new DefaultComboBoxModel<>(Gender.values());
//    private final ComboBoxModel<Department> departmentModel;
//    private final DateModel<LocalDate> birthDateModel = new LocalDateModel();
//
//    private final Employee employee;
//
//    public OLDEmployeeDialog(Employee employee, ListModel<Department> departmentModel) {
//        this.employee = employee;
//        this.departmentModel = new ComboBoxModelAdapter<>(departmentModel);
//        setValues();
//        addFields();
//    }
//
//    private void setValues() {
//        firstNameField.setText(employee.getFirstName());
//        lastNameField.setText(employee.getLastName());
//        genderModel.setSelectedItem(employee.getGender());
//        departmentModel.setSelectedItem(employee.getDepartment());
//        birthDateModel.setValue(employee.getBirthDate());
//    }
//
//    private void addFields() {
//        var deparmentComboBox = new JComboBox<>(departmentModel);
//        deparmentComboBox.setRenderer(new DepartmentRenderer());
//
//        add("First Name:", firstNameField);
//        add("Last Name:", lastNameField);
//        add("Gender:", new JComboBox<>(genderModel));
//        add("Birth Date:", new JDatePicker(birthDateModel));
//        add("Department:", deparmentComboBox);
//    }
//
//    @Override
//    Employee getEntity() {
//        employee.setFirstName(firstNameField.getText());
//        employee.setLastName(lastNameField.getText());
//        employee.setGender((Gender) genderModel.getSelectedItem());
//        employee.setDepartment((Department) departmentModel.getSelectedItem());
//        employee.setBirthDate(birthDateModel.getValue());
//        return employee;
//    }
//}
