package cz.muni.fi.pv168.project.gui.panels;

import cz.muni.fi.pv168.project.gui.table.RideTable;
import cz.muni.fi.pv168.project.gui.table.RideTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

/**
 * @author Sabrina Orálková, 525089
 */
public class CarRidesTablePanel extends JPanel{

    private JTable table;

    public CarRidesTablePanel(RideTable rideTable){
        setLayout(new BorderLayout());
        table = setUpTable(rideTable);
    }

    private JTable setUpTable(RideTableModel rideTableModel){
        var table = new JTable(rideTableModel);

        table.setAutoCreateRowSorter(true);
        //table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        return table;
    }

    //private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        //var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        //var count = selectionModel.getSelectedItemsCount();
        //if (onSe)
    //}



}
