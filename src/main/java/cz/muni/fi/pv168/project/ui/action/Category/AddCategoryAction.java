package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class AddCategoryAction extends AbstractAction {

    private final JTable categoryTable;

    private final ListModel<Category> categoryListModel;
    private BufferedImage addImage;


    AddCategoryAction(JTable categoryTable, EntityListModelAdapter<Category> categoryListModel) {
        super("Add");

        try {
            addImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/add.png"));
            Icon customIcon = new ImageIcon(addImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

        this.categoryTable = categoryTable;
        this.categoryListModel = categoryListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Category");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var categoryTableModel = (CategoryTableModel) categoryTable.getModel();
        var dialog = new CategoryDialog(createPreffiledCateogory(), categoryListModel);
        dialog.show(categoryTable, "Add category")
                .ifPresent(categoryTableModel::addRow);
    }

    private Category createPreffiledCateogory() {
        var testDataGenerator = new TestDataGenerator();
        return testDataGenerator.createBlankCategory();
    }
}
