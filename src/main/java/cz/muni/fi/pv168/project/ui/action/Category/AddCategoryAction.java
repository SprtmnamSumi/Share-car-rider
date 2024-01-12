package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.dialog.EntityDialog;
import cz.muni.fi.pv168.project.ui.model.table.CategoryTableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


final class AddCategoryAction extends AbstractAction {
    private final JTable categoryTable;
    private final EntityProvider entityProvider;
    private final DialogFactory dialogFactory;

    AddCategoryAction(JTable categoryTable, DialogFactory dialogFactory, EntityProvider entityProvider, Icon icon) {
        super("Add");
        this.categoryTable = categoryTable;
        this.entityProvider = entityProvider;
        this.dialogFactory = dialogFactory;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Adds new Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        EntityDialog<Category> dialog = dialogFactory.getAddCategoryDialog(entityProvider.getCategory());
        dialog.show(categoryTable, "Add category", "Add")
                .ifPresent(categoryTableModel::addRow);
    }
}
