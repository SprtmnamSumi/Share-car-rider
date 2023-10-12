package cz.muni.fi.pv168.employees.ui.model;

import cz.muni.fi.pv168.employees.model.CarRide;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class CarRideTableModel extends AbstractTableModel implements EntityTableModel<CarRide> {

    private final List<CarRide> carRides;

    private final List<Column<CarRide, ?>> columns = List.of(
//            Column.editable("Gender", Gender.class, CarRide::getGender, CarRide::setGender),
//            Column.readonly("Age", Integer.class, this::calculateAge),
//            Column.editable("Last name", String.class, CarRide::getLastName, CarRide::setLastName),
//            Column.editable("First name", String.class, CarRide::getFirstName, CarRide::setFirstName),
//            Column.readonly("Birthdate", LocalDate.class, CarRide::getBirthDate),
//            Column.editable("Department", Department.class, CarRide::getDepartment, CarRide::setDepartment)
    );

    public CarRideTableModel(Collection<CarRide> carRides) {
        this.carRides = new ArrayList<>(carRides);
    }

    private int calculateAge(CarRide carRide) {
        return Period
                .between(carRide.getBirthDate(), LocalDate.now())
                .getYears();
    }

    @Override
    public int getRowCount() {
        return carRides.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var carRide = getEntity(rowIndex);
        return columns.get(columnIndex).getValue(carRide);
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
        if (value != null) {
            var carRide = getEntity(rowIndex);
            columns.get(columnIndex).setValue(value, carRide);
        }
    }

    public void deleteRow(int rowIndex) {
        carRides.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(CarRide carRide) {
        int newRowIndex = carRides.size();
        carRides.add(carRide);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(CarRide carRide) {
        int rowIndex = carRides.indexOf(carRide);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public CarRide getEntity(int rowIndex) {
        return carRides.get(rowIndex);
    }
}
