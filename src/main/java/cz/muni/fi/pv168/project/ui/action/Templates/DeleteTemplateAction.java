package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Comparator;

public final class DeleteTemplateAction extends AbstractAction {

    private final JTable templateTable;

    public DeleteTemplateAction(JTable templateTable) {
        super("Delete", Icons.DELETE_ICON);
        this.templateTable = templateTable;
        putValue(SHORT_DESCRIPTION, "Deletes Car Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) templateTable.getModel();
        Arrays.stream(templateTable.getSelectedRows())
                .map(templateTable::convertRowIndexToModel)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(templateTableModel::deleteRow);
    }
}
