package cz.muni.fi.pv168.project.ui.panels.Template;

import cz.muni.fi.pv168.project.ui.action.Templates.AddTemplateAction;
import cz.muni.fi.pv168.project.ui.action.Templates.DeleteTemplateAction;
import cz.muni.fi.pv168.project.ui.action.Templates.EditTemplateAction;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideListModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideFilterBar;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideStatisticsBar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with template records in a table.
 */
public class TemplateTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;

    public TemplateTablePanel(TemplateTableModel TemplateTableModel, CategoryListModel categoryListModel, CarRideListModel templateListModel) {
        setLayout(new BorderLayout());
        table = setUpTable(TemplateTableModel, categoryListModel, templateListModel);
        CarRideFilterBar filterBar = new CarRideFilterBar();
        CarRideStatisticsBar statsPanel = new CarRideStatisticsBar(TemplateTableModel);
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

    private JTable setUpTable(TemplateTableModel TemplateTableModel, CategoryListModel categoryListModel, CarRideListModel templateListModel) {
        var table = new JTable(TemplateTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCarRideAction = new AddTemplateAction(table, categoryListModel, templateListModel);
        editCarRideAction = new EditTemplateAction(table, categoryListModel, templateListModel);
        deleteCarRideAction = new DeleteTemplateAction(table);

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
