package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.bussiness.model.Entity;
import cz.muni.fi.pv168.project.bussiness.service.crud.ICrudService;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public abstract class TableModel<T extends Entity> extends AbstractTableModel implements EntityTableModel<T> {

    private final List<Column<T, ?>> columns;
    private final ICrudService<T> entityCrudService;
    private List<T> entities;

    public TableModel(ICrudService<T> entityCrudService, List<Column<T, ?>> columns) {
        this.entityCrudService = entityCrudService;
        this.entities = new ArrayList<T>(entityCrudService.findAll());
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return entities.size();
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
            var entity = getEntity(rowIndex);
            columns.get(columnIndex).setValue(value, entity);
            updateRow(entity);
        }
    }

    public void deleteRow(int rowIndex) {
        var entityToBeDeleted = getEntity(rowIndex);
        entityCrudService.deleteByGuid(entityToBeDeleted.getGuid());
        entities.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(T entity) {
        entityCrudService.create(entity)
                .intoException();
        int newRowIndex = entities.size();
        entities.add(entity);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(T entity) {
        entityCrudService.update(entity)
                .intoException();
        int rowIndex = entities.indexOf(entity);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void refresh() {
        this.entities = new ArrayList<>(entityCrudService.findAll());
        fireTableDataChanged();
    }

    @Override
    public T getEntity(int rowIndex) {
        return entities.get(rowIndex);
    }
}
