package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with car ride records in a table.
 */
public class CarRideTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;

    private final CarRideStatisticsPanel statsPanel;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;

    public CarRideTablePanel(CarRideTableModel carRideTableModel,
                             DefaultActionFactory<CarRide> actionFactory) {
        setLayout(new BorderLayout());
        table = setUpTable(carRideTableModel, actionFactory);
        var rowSorter = new TableRowSorter<>(carRideTableModel);
        table.setRowSorter(rowSorter);

        CarRideFilterPanel filterBar = new CarRideFilterPanel(new CarRideTableFilter(rowSorter));
        statsPanel = new CarRideStatisticsPanel(carRideTableModel);

        add(filterBar, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.PAGE_END);

        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CarRideTableModel carRideTableModel,
                              DefaultActionFactory<CarRide> carRideActionFactory) {
        var table = new JTable(carRideTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.getModel().addTableModelListener(e -> updateStats());

        addCarRideAction = carRideActionFactory.getAddAction(table);
        editCarRideAction = carRideActionFactory.getEditAction(table);
        deleteCarRideAction = carRideActionFactory.getDeleteAction(table);

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

    private void updateStats() {
        statsPanel.updateFilteredStats();
        statsPanel.updateTotalStats();
    }
}
