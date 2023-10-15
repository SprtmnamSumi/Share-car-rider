package cz.muni.fi.pv168.project.ui.model.Template;

import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.entities.Template;
import cz.muni.fi.pv168.project.ui.model.Column;
import cz.muni.fi.pv168.project.ui.model.EntityTableModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link javax.swing.table.TableModel} for {@link Template} objects.
 */
public class TemplateTableModel extends AbstractTableModel implements EntityTableModel<Template> {

    private final List<Template> templates;

    private final List<Column<Template, ?>> columns = List.of(
            Column.editable("Name", String.class, Template::getTitle, Template::setTitle),
            Column.editable("Distance", Double.class, Template::getDistance, Template::setDistance),
            Column.editable("Category", Category.class, Template::getCategory, Template::setCategory)
    );

    public TemplateTableModel(List<Template> templates) {
        this.templates = new ArrayList<>(templates);
    }

    @Override
    public int getRowCount() {
        return templates.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Template template = getEntity(rowIndex);
        return columns.get(columnIndex).getValue(template);
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
            Template template = getEntity(rowIndex);
            columns.get(columnIndex).setValue(value, template);
        }
    }

    public void deleteRow(int rowIndex) {
        templates.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Template template) {
        int newRowIndex = templates.size();
        templates.add(template);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Template template) {
        int rowIndex = templates.indexOf(template);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public Template getEntity(int rowIndex) {
        return templates.get(rowIndex);
    }
}
