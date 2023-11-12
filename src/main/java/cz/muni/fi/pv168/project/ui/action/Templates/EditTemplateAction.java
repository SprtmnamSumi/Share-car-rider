package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

final class EditTemplateAction extends AbstractAction {

    private final JTable templateTable;
    private final DialogFactory dialogFactory;
    private BufferedImage editImage;

    EditTemplateAction(JTable templateTable, DialogFactory dialogFactory) {
        super("Edit");

        try {
            editImage = ImageIO.read(new File("src/main/java/cz/muni/fi/pv168/project/ui/icons/editing.png"));
            Icon customIcon = new ImageIcon(editImage);
            putValue(SMALL_ICON, customIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        };

        this.templateTable = templateTable;
        this.dialogFactory = dialogFactory;
        putValue(SHORT_DESCRIPTION, "Edits Car Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = templateTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (templateTable.isEditing()) {
            templateTable.getCellEditor().cancelCellEditing();
        }
        TemplateTableModel templateListModel = (TemplateTableModel) templateTable.getModel();
        int modelRow = templateTable.convertRowIndexToModel(selectedRows[0]);
        Template template = templateListModel.getEntity(modelRow);
        var dialog = dialogFactory.getAddTemplateDialog(template);
        dialog.show(templateTable, "Edit Template", "Save")
                .ifPresent(templateListModel::updateRow);
    }
}
