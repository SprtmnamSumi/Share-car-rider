package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Model;
import cz.muni.fi.pv168.project.business.service.crud.ICrudService;
import cz.muni.fi.pv168.project.business.service.validation.ValidationResult;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * {@link javax.swing.table.TableModel} for {@link CarRide} objects.
 */
public abstract class TableModel<T extends Model> extends AbstractTableModel implements EntityTableModel<T> {

    private final List<Column<T, ?>> columns;
    private final ICrudService<T> entityCrudService;
    private List<T> entities;

    public TableModel(ICrudService<T> entityCrudService, List<Column<T, ?>> columns) {
        this.entityCrudService = entityCrudService;
        this.entities = new ArrayList<>(entityCrudService.findAll());
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
        T entityToBeDeleted = getEntity(rowIndex);
        ValidationResult result = entityCrudService.deleteByGuid(entityToBeDeleted.getGuid());
        if (result.getValidationErrors().isEmpty()) {
            entities.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        } else {
            showDeleteAlert(rowIndex, result.getValidationErrors());
        }
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

    public List<T> getAllEntities() {
        return List.copyOf(entities);
    }

    @Override
    public T getEntity(int rowIndex) {
        return entities.get(rowIndex);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(entities);
    }

    private void showDeleteAlert(int row, List<String> errors) {
        StringBuilder message = new StringBuilder()
                .append(String.format("Data item at %d row could not be deleted. Reason: ", row));
        for (String error : errors) {
            message.append(error).append(System.lineSeparator());
        }
        JOptionPane.showMessageDialog(Frame.getFrames()[0], message.toString());
    }
}
