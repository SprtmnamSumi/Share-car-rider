package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.ui.action.Category.AddCategoryAction;
import cz.muni.fi.pv168.project.ui.action.Category.DeleteCategoryAction;
import cz.muni.fi.pv168.project.ui.action.Category.EditCategoryAction;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with category records in a table.
 */
public class CategoryTablePanel extends JPanel {

    private final JTable table;
    private final Consumer<Integer> onSelectionChange;
    private Action addCategoryAction;
    private Action editCategoryAction;
    private Action deleteCategoryAction;

    public CategoryTablePanel(CategoryTableModel categoryTableModel, CategoryListModel categoryListModel) {
        setLayout(new BorderLayout());
        table = setUpTable(categoryTableModel, categoryListModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CategoryTableModel categoryTableModel, CategoryListModel categoryListModel) {
        var table = new JTable(categoryTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCategoryAction = new AddCategoryAction(table, categoryListModel);
        editCategoryAction = new EditCategoryAction(table, categoryListModel);
        deleteCategoryAction = new DeleteCategoryAction(table);

        table.setComponentPopupMenu(createCategoryTablePopUpMenu());


        return table;
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
