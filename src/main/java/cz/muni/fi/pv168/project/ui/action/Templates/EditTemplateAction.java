package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;

final class EditTemplateAction extends AbstractAction {

    private final JTable templateTable;
    private final DialogFactory dialogFactory;

    EditTemplateAction(JTable templateTable, DialogFactory dialogFactory, Icon icon) {
        super("Edit");
        this.templateTable = templateTable;
        this.dialogFactory = dialogFactory;
        putValue(SMALL_ICON, icon);
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
