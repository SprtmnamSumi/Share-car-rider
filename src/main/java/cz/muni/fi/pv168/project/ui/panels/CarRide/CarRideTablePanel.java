package cz.muni.fi.pv168.project.ui.panels.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.service.statistics.ICarRideStatistics;
import cz.muni.fi.pv168.project.ui.NotificationController;
import cz.muni.fi.pv168.project.ui.action.CarRide.ICarRideActionFactory;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.filters.CarRideTableFilter;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.Currency.CurrencyTableModel;
import cz.muni.fi.pv168.project.ui.panels.AbstractTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTableCell;
import java.awt.BorderLayout;
import java.util.function.Consumer;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableRowSorter;

/**
 * Panel with car ride records in a table.
 */

public class CarRideTablePanel extends AbstractTablePanel {

    private final Consumer<Integer> onSelectionChange;
    private final CarRideStatisticsPanel statsPanel;
    private final CarRideFilterPanel filterPanel;
    private final CarRideTableFilter filter;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;
    private Action saveAsTemplateAction;

    public CarRideTablePanel(CarRideTableModel carRideTableModel,
                             ICarRideActionFactory actionFactory,
                             CategoryTableModel categoryTableModel,
                             CurrencyTableModel currencyTableModel,
                             ICarRideStatistics ICarRideStatistics
    ) {
        super(carRideTableModel, new TableRowSorter<>(carRideTableModel));
        setUpTable(actionFactory);

        filter = new CarRideTableFilter((TableRowSorter<CarRideTableModel>) table.getRowSorter());
        filterPanel = new CarRideFilterPanel(filter, categoryTableModel, currencyTableModel);
        statsPanel = new CarRideStatisticsPanel(carRideTableModel, filter, ICarRideStatistics);
        table.getRowSorter().addRowSorterListener(e -> statsPanel.updateFilteredStats());
        categoryTableModel.addTableModelListener(e -> updateStats());

        add(filterPanel, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.PAGE_END);

        this.onSelectionChange = this::changeActionsState;
    }

    public CarRideTableFilter getFilter() {
        return filter;
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
        popupMenu.add(saveAsTemplateAction);
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
        SwingUtilities.invokeLater(() -> {
            statsPanel.updateFilteredStats();
            statsPanel.updateTotalStats();
            filterPanel.updateValues();
        });
    }
}
