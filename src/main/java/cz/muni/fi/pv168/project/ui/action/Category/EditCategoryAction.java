package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class EditCategoryAction extends AbstractAction {

    private final JTable categoryTable;

    private final ListModel<Category> categoriestListModel;

    public EditCategoryAction(JTable categoryTable, ListModel<Category> categoriestListModel) {
        super("Edit", Icons.ADD_ICON);
        this.categoryTable = categoryTable;
        this.categoriestListModel = categoriestListModel;
        putValue(SHORT_DESCRIPTION, "Edits Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = categoryTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (categoryTable.isEditing()) {
            categoryTable.getCellEditor().cancelCellEditing();
        }
        CategoryTableModel categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        int modelRow = categoryTable.convertRowIndexToModel(selectedRows[0]);
        Category category = categoryTableModel.getEntity(modelRow);
        CategoryDialog categoryDialog = new CategoryDialog(category, categoriestListModel);
        categoryDialog.show(categoryTable, "Edit Category Ride")
                .ifPresent(categoryTableModel::updateRow);
    }
}
