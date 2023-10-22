package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.ui.action.CarRide.AddCarRideAction;
import cz.muni.fi.pv168.project.ui.action.CarRide.DeleteCarRideAction;
import cz.muni.fi.pv168.project.ui.action.CarRide.EditCarRideAction;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideListModel;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with car ride records in a table.
 */
public class CarRideTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;

    public CarRideTablePanel(CarRideTableModel carRideTableModel, CategoryListModel categoryListModel, CarRideListModel templateListModel) {
        setLayout(new BorderLayout());
        table = setUpTable(carRideTableModel, categoryListModel, templateListModel);
        CarRideFilterBar filterBar = new CarRideFilterBar();
        CarRideStatisticsBar statsPanel = new CarRideStatisticsBar(carRideTableModel);
        table.getModel().addTableModelListener(e ->
        {
            statsPanel.updateFilteredStats();
            statsPanel.updateTotalStats();
        });

        add(filterBar, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.PAGE_END);

        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CarRideTableModel carRideTableModel, CategoryListModel categoryListModel, CarRideListModel templateListModel) {
        var table = new JTable(carRideTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCarRideAction = new AddCarRideAction(table, categoryListModel, templateListModel);
        editCarRideAction = new EditCarRideAction(table, categoryListModel, templateListModel);
        deleteCarRideAction = new DeleteCarRideAction(table);

        table.setComponentPopupMenu(createCarRideTablePopUpMenu());

        return table;
    }

    private JPopupMenu createCarRideTablePopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(addCarRideAction);
        popupMenu.add(editCarRideAction);
        popupMenu.add(deleteCarRideAction);
        return popupMenu;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        var count = selectionModel.getSelectedItemsCount();
        if (onSelectionChange != null) {
            onSelectionChange.accept(count);
        }
    }

    private void changeActionsState(int selectedItemsCount) {
        editCarRideAction.setEnabled(selectedItemsCount == 1);
        deleteCarRideAction.setEnabled(selectedItemsCount >= 1);
    }
}
