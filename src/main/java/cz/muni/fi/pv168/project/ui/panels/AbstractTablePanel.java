package cz.muni.fi.pv168.project.ui.panels;

import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;


public abstract class AbstractTablePanel extends JPanel {

    protected final JTable table;

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

    private void setUpTable() {
        setLayout(new BorderLayout());
        table.getRowSorter().toggleSortOrder(0);
        table.getRowSorter().toggleSortOrder(0);
        table.getTableHeader().setComponentPopupMenu(columnPopupMenu(table.getColumnModel()));
    }

    private JPopupMenu columnPopupMenu(TableColumnModel columnModel) {
        JPopupMenu menu = new JPopupMenu();
        Iterator<TableColumn> columns = columnModel.getColumns().asIterator();
        while (columns.hasNext()) {
            TableColumn column = columns.next();
            JButton menuItem = new JButton((String) column.getHeaderValue());
            menuItem.setBackground(Color.green);
            menuItem.addActionListener((a) -> {
                if (column.getMinWidth() == 0) {
                    menuItem.setBackground(Color.green);
                    showColumn(column);
                } else {
                    hideColumn(column);
                    menuItem.setBackground(Color.red);
                }
            });
            menu.add(menuItem);
        }
        return menu;
    }

    private void showColumn(TableColumn column) {
        column.setMaxWidth(1000);
        column.setMinWidth(200);
    }

    private void hideColumn(TableColumn column) {
        column.setMinWidth(0);
        column.setMaxWidth(0);
    }
}
