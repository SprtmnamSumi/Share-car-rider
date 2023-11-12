package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class AddTemplateAction extends AbstractAction {

    private final JTable templateTable;
    private final DialogFactory dialogFactory;
    private BufferedImage addImage;

    AddTemplateAction(JTable templateTable, DialogFactory dialogFactory) {
        super("Add");

        try {
            addImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/add.png"));
            Icon customIcon = new ImageIcon(addImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

        this.templateTable = templateTable;
        this.dialogFactory = dialogFactory;
        putValue(SHORT_DESCRIPTION, "Adds new template");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) templateTable.getModel();
        var dialog = dialogFactory.getAddTemplateDialog(createPreffiledTemplate());
        dialog.show(templateTable, "Add Template")
                .ifPresent(templateTableModel::addRow);
    }

    private Template createPreffiledTemplate() {
        var testDataGenerator = new TestDataGenerator();
        return testDataGenerator.createBlankTemplate();
    }
}
