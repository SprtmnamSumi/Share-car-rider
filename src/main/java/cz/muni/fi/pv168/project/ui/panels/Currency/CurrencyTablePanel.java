package cz.muni.fi.pv168.project.ui.panels.Currency;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.AbstractTablePanel;
import cz.muni.fi.pv168.project.ui.panels.Category.CategoryTableCell;
import cz.muni.fi.pv168.project.util.ConversionUtils;
import java.awt.BorderLayout;
import java.util.function.Consumer;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Panel with currency records in a table.
 */

public class CurrencyTablePanel extends AbstractTablePanel {

    private final Consumer<Integer> onSelectionChange;
    private Action addCurrencyAction;
    private Action editCurrencyAction;
    private Action deleteCurrencyAction;

    public CurrencyTablePanel(TableModel<Currency> currencyTableModel,
                              DefaultActionFactory<Currency> actionFactory) {
        super(currencyTableModel);
        setUpTable(actionFactory);
        add(new JScrollPane(table), BorderLayout.CENTER);
        this.onSelectionChange = this::changeActionsState;
    }

    private void setUpTable(DefaultActionFactory<Currency> actionFactory) {
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.setDefaultRenderer(Category.class, (table, value, isSelected, hasFocus, row, column) -> new CategoryTableCell((Category) value));

        addCurrencyAction = actionFactory.getAddAction(table);
        editCurrencyAction = actionFactory.getEditAction(table);
        deleteCurrencyAction = actionFactory.getDeleteAction(table);
        changeActionsState(0);

        table.setComponentPopupMenu(createCurrencyTablePopUpMenu());
    }

    private JPopupMenu createCurrencyTablePopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(addCurrencyAction);
        popupMenu.add(editCurrencyAction);
        popupMenu.add(deleteCurrencyAction);
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
        editCurrencyAction.setEnabled(selectedItemsCount == 1);
        deleteCurrencyAction.setEnabled(selectedItemsCount >= 1);
    }
}
