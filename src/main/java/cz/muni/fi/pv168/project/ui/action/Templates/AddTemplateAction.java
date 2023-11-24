package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.KeyStroke;

final class AddTemplateAction extends AbstractAction {

    private final JTable templateTable;
    private final DialogFactory dialogFactory;

    AddTemplateAction(JTable templateTable, DialogFactory dialogFactory, Icon icon) {
        super("Add");
        this.templateTable = templateTable;
        this.dialogFactory = dialogFactory;
        putValue(SMALL_ICON, icon);
        putValue(SHORT_DESCRIPTION, "Adds new template");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) templateTable.getModel();
        var dialog = dialogFactory.getAddTemplateDialog(createPreffiledTemplate());
        dialog.show(templateTable, "Add Template", "Add")
                .ifPresent(templateTableModel::addRow);
    }

    private Template createPreffiledTemplate() {
        var testDataGenerator = new TestDataGenerator();
        return testDataGenerator.createBlankTemplate();
    }
}
