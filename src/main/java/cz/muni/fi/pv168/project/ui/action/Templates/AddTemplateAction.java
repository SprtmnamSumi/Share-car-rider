package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.TableModel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class AddTemplateAction extends AbstractAction {
    private final JTable templateTable;
    private final EntityProvider entityProvider;
    private final DialogFactory dialogFactory;

    AddTemplateAction(JTable templateTable, DialogFactory dialogFactory, EntityProvider entityProvider, Icon icon) {
        super("Add");
        this.templateTable = templateTable;
        this.dialogFactory = dialogFactory;
        this.entityProvider = entityProvider;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Adds new template");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TableModel<Template> templateTableModel = (TableModel) templateTable.getModel();
        var dialog = dialogFactory.getAddTemplateDialog(entityProvider.getTemplate());
        dialog.show(templateTable, "Add Template", "Add")
                .ifPresent(templateTableModel::addRow);
    }
}
