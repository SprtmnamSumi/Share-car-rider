package cz.muni.fi.pv168.project.ui.action.Category;

import cz.muni.fi.pv168.project.ui.model.Category.CategoryTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

final class DeleteCategoryAction extends AbstractAction {

    private final JTable categoryTable;
    private BufferedImage deleteImage;

    DeleteCategoryAction(JTable categoryTable) {
        super("Delete");

        try {
            deleteImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/bin.png"));
            Icon customIcon = new ImageIcon(deleteImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

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
