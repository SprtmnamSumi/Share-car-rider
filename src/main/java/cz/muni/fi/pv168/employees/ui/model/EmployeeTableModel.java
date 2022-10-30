package cz.muni.fi.pv168.employees.ui.model;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;
import cz.muni.fi.pv168.employees.model.Gender;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link Employee} objects.
 */
public class EmployeeTableModel extends AbstractTableModel implements EntityTableModel<Employee> {

    private final List<Employee> employees;

    private final List<Column<Employee, ?>> columns = List.of(
            Column.editable("Gender", Gender.class, Employee::getGender, Employee::setGender),
            Column.readonly("Age", Integer.class, this::calculateAge),
            Column.editable("Last name", String.class, Employee::getLastName, Employee::setLastName),
            Column.editable("First name", String.class, Employee::getFirstName, Employee::setFirstName),
            Column.readonly("Birthdate", LocalDate.class, Employee::getBirthDate),
            Column.editable("Department", Department.class, Employee::getDepartment, Employee::setDepartment)
    );

    private int calculateAge(Employee employee) {
        return Period
                .between(employee.getBirthDate(), LocalDate.now())
                .getYears();
    }

    public EmployeeTableModel(Collection<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var employee = getEntity(rowIndex);
        return columns.get(columnIndex).getValue(employee);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getColumnType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        var employee = getEntity(rowIndex);
        columns.get(columnIndex).setValue(value, employee);
    }

    public void deleteRow(int rowIndex) {
        employees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Employee employee) {
        int newRowIndex = employees.size();
        employees.add(employee);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Employee employee) {
        int rowIndex = employees.indexOf(employee);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public Employee getEntity(int rowIndex) {
        return employees.get(rowIndex);
    }
}
