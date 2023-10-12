package cz.muni.fi.pv168.employees.ui.action;

import cz.muni.fi.pv168.employees.data.TestDataGenerator;
import cz.muni.fi.pv168.employees.model.Category;
import cz.muni.fi.pv168.employees.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.employees.ui.dialog.RideDialog;
import cz.muni.fi.pv168.employees.ui.model.CarRideTableModel;
import cz.muni.fi.pv168.employees.ui.model.CategoryListModel;
import cz.muni.fi.pv168.employees.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddCategoryAction extends AbstractAction {

    private final JTable categoryTable;
    private final TestDataGenerator testDataGenerator;


    public AddCategoryAction(JTable categoryTable, TestDataGenerator testDataGenerator) {
        super("Add", Icons.ADD_ICON);
        this.categoryTable = categoryTable;
        this.testDataGenerator = testDataGenerator;
        putValue(SHORT_DESCRIPTION, "Adds new Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryListModel) categoryTable.getModel();
        var dialog = new CategoryDialog(testDataGenerator.crateTestCategory());
//        dialog.show(categoryTable, "Add Car ride")
//                .ifPresent(categoryTableModel::addRow);
    }
}
