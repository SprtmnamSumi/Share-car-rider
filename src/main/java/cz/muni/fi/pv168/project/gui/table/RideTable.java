package cz.muni.fi.pv168.project.gui.table;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Class instance of table filled with rides.
 */
public class RideTable extends JTable {
    TableColumn dateColumn = new TableColumn();
    TableColumn priceColumn = new TableColumn();
    public RideTable() {
        super();
        this.setPreferredSize(new Dimension(1920,1080));
        dateColumn.setHeaderValue("Date");
        this.addColumn(dateColumn);
        priceColumn.setHeaderValue("Price");
        this.addColumn(priceColumn);
    }
}
