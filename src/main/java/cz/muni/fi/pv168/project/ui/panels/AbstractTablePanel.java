package cz.muni.fi.pv168.project.ui.panels;

import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public abstract class AbstractTablePanel extends JPanel {

    protected JTable table;

    public AbstractTablePanel(TableModel model) {
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        setUpTable();
    }
    public AbstractTablePanel(TableModel model, TableRowSorter rowSorter) {
        table = new JTable(model);
        table.setRowSorter(rowSorter);
        setUpTable();
    }

    public JTable getTable() {
        return table;
    }

    private void setUpTable(){
        setLayout(new BorderLayout());
        table.getRowSorter().toggleSortOrder(0);
        table.getRowSorter().toggleSortOrder(0);
    }
}
