package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;

import cz.muni.fi.pv168.project.business.service.statistics.ICarRideStatistics;

import cz.muni.fi.pv168.project.business.model.Category;

import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTableCell;
import cz.muni.fi.pv168.project.ui.panels.AbstractTablePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with car ride records in a table.
 */
public class CarRideTablePanel extends AbstractTablePanel<CarRide> {

    private final Consumer<Integer> onSelectionChange;

    private final CarRideStatisticsPanel statsPanel;

    private final CarRideFilterPanel filterPanel;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;

    public CarRideTablePanel(CarRideTableModel carRideTableModel,
                             DefaultActionFactory<CarRide> actionFactory,
                             CategoryTableModel categoryTableModel,
                             CurrencyTableModel currencyTableModel,
                             ICarRideStatistics ICarRideStatistics) {
        super(carRideTableModel);

        var rowSorter = new TableRowSorter<>(carRideTableModel);
        setUpTable(actionFactory);
        table.setRowSorter(rowSorter);

        filterPanel = new CarRideFilterPanel(new CarRideTableFilter(rowSorter), categoryTableModel, currencyTableModel);
        categoryTableModel.addTableModelListener(e -> updateStats());
        statsPanel = new CarRideStatisticsPanel(carRideTableModel, ICarRideStatistics);

        add(filterPanel, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.PAGE_END);

        this.onSelectionChange = this::changeActionsState;
    }

    private void setUpTable(DefaultActionFactory<CarRide> carRideActionFactory) {
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.setDefaultRenderer(Category.class, (table, value, isSelected, hasFocus, row, column) -> new CategoryTableCell((Category) value));
        table.getModel().addTableModelListener(e -> updateStats());

        addCarRideAction = carRideActionFactory.getAddAction(table);
        editCarRideAction = carRideActionFactory.getEditAction(table);
        deleteCarRideAction = carRideActionFactory.getDeleteAction(table);
        changeActionsState(0);

        table.setComponentPopupMenu(createCarRideTablePopUpMenu());
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
        filterPanel.updateValues();
    }
}
