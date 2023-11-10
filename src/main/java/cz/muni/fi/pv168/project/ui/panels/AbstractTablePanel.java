package cz.muni.fi.pv168.project.ui.panels;

import cz.muni.fi.pv168.project.business.model.Entity;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractTablePanel<T extends Entity> extends JPanel {
    protected JTable table;

    public AbstractTablePanel(TableModel<T> tableModel){
        table = new JTable(tableModel){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        this.setLayout(new BorderLayout());
        table.setAutoCreateRowSorter(true);
    }
    public JTable getTable() {
        return table;
    }
}
