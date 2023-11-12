package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.EntityDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class EditCategoryAction extends AbstractAction {

    private final JTable categoryTable;

    private final DialogFactory dialogFactory;
    private BufferedImage editImage;

    EditCategoryAction(JTable categoryTable, DialogFactory dialogFactory) {
        super("Edit");

        try {
            editImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/editing.png"));
            Icon customIcon = new ImageIcon(editImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

        this.categoryTable = categoryTable;
        this.dialogFactory = dialogFactory;
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
        categoryDialog.show(categoryTable, "Edit Category Ride")
                .ifPresent(categoryTableModel::updateRow);
    }
}
