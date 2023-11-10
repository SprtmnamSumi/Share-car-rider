package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with category records in a table.
 */
public class CategoryTablePanel extends JPanel {

    private JTable table;

    private final Consumer<Integer> onSelectionChange;
    private Action addCategoryAction;
    private Action editCategoryAction;
    private Action deleteCategoryAction;

    public CategoryTablePanel(CategoryTableModel categoryTableModel, DefaultActionFactory<Category> actionFactory) {
        setUpTable(categoryTableModel, actionFactory);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private void setUpTable(CategoryTableModel categoryTableModel, DefaultActionFactory<Category> actionFactory) {
        table = new JTable(categoryTableModel);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        table.setDefaultRenderer(Integer.class, (table, value, isSelected, hasFocus, row, column) -> {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setBackground(new Color((Integer) value));
            return renderer;
        });

        addCategoryAction = actionFactory.getAddAction(table);
        editCategoryAction = actionFactory.getEditAction(table);
        deleteCategoryAction = actionFactory.getDeleteAction(table);
        changeActionsState(0);

        table.setComponentPopupMenu(createCategoryTablePopUpMenu());
    }

    private JPopupMenu createCategoryTablePopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(addCategoryAction);
        popupMenu.add(editCategoryAction);
        popupMenu.add(deleteCategoryAction);
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
    }
}
