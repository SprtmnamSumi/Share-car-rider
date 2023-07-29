package cz.muni.fi.pv168.employees.ui.filters.matchers;

import cz.muni.fi.pv168.employees.ui.model.EntityTableModel;

import javax.swing.RowFilter;

/**
 * General entity matcher which can be extended by implementing the {@link EntityMatcher#evaluate(Object)}
 * method.
 *
 * @param <T> entity type
 */
public abstract class EntityMatcher<T> extends RowFilter<EntityTableModel<T>, Integer> {

    @Override
    public boolean include(Entry<? extends EntityTableModel<T>, ? extends Integer> entry) {
        EntityTableModel<T> tableModel = entry.getModel();
        int rowIndex = entry.getIdentifier();
        T entity = tableModel.getEntity(rowIndex);

        return evaluate(entity);
    }

    public abstract boolean evaluate(T entity);
}
