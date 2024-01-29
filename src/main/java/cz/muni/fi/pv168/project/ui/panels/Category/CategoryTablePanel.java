package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.action.IOActionFactory;
import cz.muni.fi.pv168.project.ui.model.TableModel;
import cz.muni.fi.pv168.project.ui.panels.AbstractTablePanel;
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
 * Panel with category records in a table.
 */

public class CategoryTablePanel extends AbstractTablePanel {

    private final Consumer<Integer> onSelectionChange;
    private Action addCategoryAction;
    private Action editCategoryAction;
    private Action deleteCategoryAction;
    private Action exportSelectionAction;

    public CategoryTablePanel(TableModel<Category> categoryTableModel,
                              DefaultActionFactory<Category> actionFactory,
                              IOActionFactory ioActionFactory) {
        super(categoryTableModel);
        setUpTable(actionFactory, ioActionFactory);
        add(new JScrollPane(table), BorderLayout.CENTER);
        this.onSelectionChange = this::changeActionsState;
    }

    private void setUpTable(DefaultActionFactory<Category> actionFactory, IOActionFactory ioActionFactory) {
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.setDefaultRenderer(Integer.class, (table, value, isSelected, hasFocus, row, column) -> {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setBackground(ConversionUtils.getDimColor((Integer) value));
            return renderer;
        });
        
        addCategoryAction = actionFactory.getAddAction(table);
        editCategoryAction = actionFactory.getEditAction(table);
        deleteCategoryAction = actionFactory.getDeleteAction(table);
        exportSelectionAction = ioActionFactory.getExportSelectionAction(table);

        changeActionsState(0);

        table.setComponentPopupMenu(createCategoryTablePopUpMenu());
    }

    private JPopupMenu createCategoryTablePopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(addCategoryAction);
        popupMenu.add(editCategoryAction);
        popupMenu.add(deleteCategoryAction);
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
        editCategoryAction.setEnabled(selectedItemsCount == 1);
        deleteCategoryAction.setEnabled(selectedItemsCount >= 1);
        exportSelectionAction.setEnabled(selectedItemsCount >= 1);
    }
}
