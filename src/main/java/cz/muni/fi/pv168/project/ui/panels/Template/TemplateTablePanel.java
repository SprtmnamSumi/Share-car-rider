package cz.muni.fi.pv168.project.ui.panels.Template;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideFilterPanel;
import cz.muni.fi.pv168.project.ui.panels.CarRide.CarRideStatisticsPanel;

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

    public TemplateTablePanel(TemplateTableModel templateTableModel, DefaultActionFactory<Template> actionFactory) {
        setLayout(new BorderLayout());
        table = setUpTable(templateTableModel, actionFactory);
        CarRideFilterPanel filterBar = new CarRideFilterPanel();

        add(filterBar, BorderLayout.PAGE_START);
        add(new JScrollPane(table), BorderLayout.CENTER);

        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(TemplateTableModel TemplateTableModel, DefaultActionFactory<Template> actionFactory) {
        var table = new JTable(TemplateTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCarRideAction = actionFactory.getAddAction(table);
        editCarRideAction = actionFactory.getEditAction(table);
        deleteCarRideAction = actionFactory.getDeleteAction(table);

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
