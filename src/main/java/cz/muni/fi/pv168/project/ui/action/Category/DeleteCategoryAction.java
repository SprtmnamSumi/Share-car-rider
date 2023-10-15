package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;

public final class DeleteCategoryAction extends AbstractAction {

    private final JTable categoryTable;

    public DeleteCategoryAction(JTable categoryTable) {
        super("Delete", Icons.DELETE_ICON);
        this.categoryTable = categoryTable;
        putValue(SHORT_DESCRIPTION, "Deletes Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        Arrays.stream(categoryTable.getSelectedRows())
                .map(categoryTable::convertRowIndexToModel)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(categoryTableModel::deleteRow);
    }
}
