package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.EntityDialog;
import cz.muni.fi.pv168.project.ui.model.table.CategoryTableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class EditCategoryAction extends AbstractAction {
    private final JTable categoryTable;
    private final DialogFactory dialogFactory;

    EditCategoryAction(JTable categoryTable, DialogFactory dialogFactory, Icon icon) {
        super("Edit");
        this.categoryTable = categoryTable;
        this.dialogFactory = dialogFactory;
        putValue(SMALL_ICON, icon);
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

        EntityDialog<Category> categoryDialog = dialogFactory.getAddCategoryDialog(category);
        categoryDialog.show(categoryTable, "Edit Category Ride", "Edit")
                .ifPresent(categoryTableModel::updateRow);
    }
}
