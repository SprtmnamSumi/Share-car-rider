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
    private final String[] tableHead = {"Date", "Name", "Distance", "Category"};
    private final DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);

    public RideTable(Dimension dimension) {
        super();
        this.setModel(tableModel);

        // Set look
        this.setGridColor(Color.GRAY);
        this.setBackground(Color.WHITE);
        this.setShowGrid(true);
        this.setPreferredSize(dimension);

        // Testing table row
        String[] item = {"1.1.2023", "Zaphod Beeblebrox", "42", "The starship Heart of Gold"};
        tableModel.addRow(item);

        // Add context menu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Edit"));
        popupMenu.add(new JMenuItem("Delete"));
        this.setComponentPopupMenu(popupMenu);
    }
}
