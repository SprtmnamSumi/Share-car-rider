package cz.muni.fi.pv168.project.ui.panels.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.panels.TablePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel with category records in a table.
 */
public class CategoryTablePanel extends TablePanel<Category> {

    private final Consumer<Integer> onSelectionChange;
    private Action addCategoryAction;
    private Action editCategoryAction;
    private Action deleteCategoryAction;

    public CategoryTablePanel(CategoryTableModel categoryTableModel, DefaultActionFactory<Category> actionFactory) {
        super(categoryTableModel);
        setUpTable(actionFactory);
        add(new JScrollPane(table), BorderLayout.CENTER);

        this.onSelectionChange = this::changeActionsState;
    }

    private void setUpTable(DefaultActionFactory<Category> actionFactory) {
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);

        addCategoryAction = actionFactory.getAddAction(table);
        editCategoryAction = actionFactory.getEditAction(table);
        deleteCategoryAction = actionFactory.getDeleteAction(table);

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
