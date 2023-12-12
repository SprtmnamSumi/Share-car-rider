package cz.muni.fi.pv168.project.ui.panels.Template;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.action.IOActionFactory;
import cz.muni.fi.pv168.project.ui.model.table.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.panels.AbstractTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTableCell;

import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.BorderLayout;
import java.util.function.Consumer;

/**
 * Panel with template records in a table.
 */

public class TemplateTablePanel extends AbstractTablePanel {
    private final Consumer<Integer> onSelectionChange;
    private Action addCarRideAction;
    private Action editCarRideAction;
    private Action deleteCarRideAction;
    private Action exportSelectionAction;

    public TemplateTablePanel(TemplateTableModel templateTableModel,
                              DefaultActionFactory<Template> actionFactory,
                              IOActionFactory ioActionFactory) {
        super(templateTableModel);
        setUpTable(actionFactory, ioActionFactory);
        add(new JScrollPane(table), BorderLayout.CENTER);
        this.onSelectionChange = this::changeActionsState;
    }

    private void setUpTable(DefaultActionFactory<Template> actionFactory, IOActionFactory ioActionFactory) {
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.setDefaultRenderer(Category.class, (table, value, isSelected, hasFocus, row, column) -> new CategoryTableCell((Category) value));

        addCarRideAction = actionFactory.getAddAction(table);
        editCarRideAction = actionFactory.getEditAction(table);
        deleteCarRideAction = actionFactory.getDeleteAction(table);
        exportSelectionAction = ioActionFactory.getExportSelectionAction(table);
        changeActionsState(0);

        table.setComponentPopupMenu(createTemplateTablePopUpMenu());
    }

    private JPopupMenu createTemplateTablePopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(addCarRideAction);
        popupMenu.add(editCarRideAction);
        popupMenu.add(deleteCarRideAction);
        popupMenu.add(exportSelectionAction);
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
        exportSelectionAction.setEnabled(selectedItemsCount >= 1);
    }
}
