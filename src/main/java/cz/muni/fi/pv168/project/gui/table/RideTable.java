package cz.muni.fi.pv168.project.gui.table;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

/**
 * Class instance of table filled with rides.
 */
public class RideTable extends JTable {
    TableColumn dateColumn = new TableColumn();
    TableColumn nameColumn = new TableColumn();
    TableColumn distanceColumn = new TableColumn();
    TableColumn categoryColumn = new TableColumn();
    public RideTable() {
        super();

        // Set look
        this.setGridColor(Color.RED);
        this.setShowGrid(true);
        this.setPreferredSize(new Dimension(1920,1080));

        // Set up columns
        dateColumn.setHeaderValue("Date");
        this.addColumn(dateColumn);
        nameColumn.setHeaderValue("Name");
        this.addColumn(nameColumn);
        distanceColumn.setHeaderValue("Distance");
        this.addColumn(distanceColumn);
        categoryColumn.setHeaderValue("Category");
        this.addColumn(categoryColumn);

        // Add context menu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Option 1"));
        popupMenu.add(new JMenuItem("Option 2"));
        this.setComponentPopupMenu(popupMenu);
    }
}
