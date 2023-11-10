package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class EditCategoryAction extends AbstractAction {

    private final JTable categoryTable;

    private final ListModel<Category> categoriestListModel;
    private BufferedImage editImage;

    EditCategoryAction(JTable categoryTable, ListModel<Category> categoriestListModel) {
        super("Edit");

        try {
            editImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/editing.png"));
            Icon customIcon = new ImageIcon(editImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

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
