package cz.muni.fi.pv168.project.gui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class instance of table filled with rides.
 */
public class RideTable extends JTable {
    private final TableColumn dateColumn = new TableColumn();
    private final TableColumn nameColumn = new TableColumn();
    private final TableColumn distanceColumn = new TableColumn();
    private final TableColumn categoryColumn = new TableColumn();
    public RideTable() {
        super();

        // Set look
        this.setGridColor(Color.GRAY);
        this.setBackground(Color.WHITE);
        this.setShowGrid(true);
        this.setPreferredSize(new Dimension(1920,1080));

        /*
        // Set up columns
        dateColumn.setHeaderValue("Date");
        this.addColumn(dateColumn);
        nameColumn.setHeaderValue("Name");
        this.addColumn(nameColumn);
        distanceColumn.setHeaderValue("Distance");
        this.addColumn(distanceColumn);
        categoryColumn.setHeaderValue("Category");
        this.addColumn(categoryColumn);
        */

        String[] tableHead={"Date","Name","Distance","Category"};
        DefaultTableModel tableModel=new DefaultTableModel(tableHead,0);
        this.setModel(tableModel);

        // Testing table row
        String[] item={"1.1.2023","Zaphod Beeblebrox","42","The starship Heart of Gold"};
        tableModel.addRow(item);

        // Add context menu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Edit"));
        popupMenu.add(new JMenuItem("Delete"));
        this.setComponentPopupMenu(popupMenu);
    }
}
