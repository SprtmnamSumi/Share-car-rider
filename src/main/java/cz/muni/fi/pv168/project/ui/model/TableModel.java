package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.entities.CarRide;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public abstract class TableModel<T> extends AbstractTableModel implements EntityTableModel<T> {

    private final List<T> carRides;
    private final List<Column<T, ?>> columns;

    public TableModel(Collection<T> carRides, List<Column<T, ?>> columns) {
        this.carRides = new ArrayList<>(carRides);
        this.columns = columns;
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

    public void addRow(T carRide) {
        int newRowIndex = carRides.size();
        carRides.add(carRide);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(T carRide) {
        int rowIndex = carRides.indexOf(carRide);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public T getEntity(int rowIndex) {
        return carRides.get(rowIndex);
    }
}
