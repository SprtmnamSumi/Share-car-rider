package cz.muni.fi.pv168.project.ui.model.CarRide;

import cz.muni.fi.pv168.project.entities.CarRide;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.EntityTableModel;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public class CarRideTableModel extends AbstractTableModel implements EntityTableModel<CarRide> {

    private final List<CarRide> carRides;

    private final List<Column<CarRide, ?>> columns = List.of(
            Column.editable("Date", LocalDateTime.class, CarRide::getDate, CarRide::setDate),
            Column.editable("Name", String.class, CarRide::getTitle, CarRide::setTitle),
            Column.editable("Distance", Double.class, CarRide::getDistance, CarRide::setDistance),
            Column.editable("Category", Category.class, CarRide::getCategory, CarRide::setCategory)
    );

    public CarRideTableModel(Collection<CarRide> carRides) {
        this.carRides = new ArrayList<>(carRides);
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
        CarRide carRide = getEntity(rowIndex);
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
            CarRide carRide = getEntity(rowIndex);
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
