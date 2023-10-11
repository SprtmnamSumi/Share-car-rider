package cz.muni.fi.pv168.project.gui.table;

import javax.swing.table.TableModel;

/**
 * @author Sabrina Orálková, 525089
 */
public interface RideTableModel<E> extends TableModel {

    E getEntity(int rowIndex);
}
