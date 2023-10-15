package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryListModel;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddCategoryAction extends AbstractAction {

    private final JTable categoryTable;
    private final TestDataGenerator testDataGenerator;
    private final ListModel<Category> categoryListModel;


    public AddCategoryAction(JTable categoryTable, TestDataGenerator testDataGenerator, CategoryListModel categoryListModel) {
        super("Add", Icons.ADD_ICON);
        this.categoryTable = categoryTable;
        this.testDataGenerator = testDataGenerator;
        this.categoryListModel = categoryListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        var dialog = new CategoryDialog(testDataGenerator.crateTestCategory(), categoryListModel);
        dialog.show(categoryTable, "Add category")
                .ifPresent(categoryTableModel::addRow);
    }
}
