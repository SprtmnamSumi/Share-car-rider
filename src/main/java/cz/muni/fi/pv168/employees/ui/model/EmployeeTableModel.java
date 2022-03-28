package cz.muni.fi.pv168.employees.ui.model;

import cz.muni.fi.pv168.employees.model.Department;
import cz.muni.fi.pv168.employees.model.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {

    private final List<Employee> employees;

    public EmployeeTableModel(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var employee = getEntity(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getFirstName();
            case 1:
                return employee.getLastName();
            case 2:
                return employee.getDepartment();
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "First Name";
            case 1:
                return "Last Name";
            case 2:
                return "Department";
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Department.class;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return true;
            case 2:
                return false;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        var employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                employee.setFirstName((String) value);
                break;
            case 1:
                employee.setLastName((String) value);
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
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

    public Employee getEntity(int rowIndex) {
        return employees.get(rowIndex);
    }
}
