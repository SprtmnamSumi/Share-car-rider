package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;

import javax.inject.Inject;
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

    @Inject
    public CategoryTablePanel(CategoryTableModel categoryTableModel, DefaultActionFactory<Category> actionFactory) {
        setLayout(new BorderLayout());
        table = setUpTable(categoryTableModel, actionFactory);
        add(new JScrollPane(table), BorderLayout.CENTER);

        this.onSelectionChange = this::changeActionsState;
    }

    public JTable getTable() {
        return table;
    }

    private JTable setUpTable(CategoryTableModel categoryTableModel, DefaultActionFactory<Category> actionFactory) {
        var table = new JTable(categoryTableModel);

        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCategoryAction = actionFactory.getAddAction(table);
        editCategoryAction = actionFactory.getEditAction(table);
        deleteCategoryAction = actionFactory.getDeleteAction(table);

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
