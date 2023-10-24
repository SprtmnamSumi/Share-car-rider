package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.TemplateDialog;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class EditTemplateAction extends AbstractAction {

    private final JTable templateTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Template> carRideTemplateListModel;

    public EditTemplateAction(JTable carRidesTable, ListModel<Category> categoriestListModel, ListModel<Template> carRideTemplateListModel) {
        super("Edit", Icons.EDIT_ICON);
        this.templateTable = carRidesTable;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
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
        TemplateDialog templateDialog = new TemplateDialog(template, categoriestListModel, carRideTemplateListModel);
        templateDialog.show(templateTable, "Edit Template")
                .ifPresent(templateListModel::updateRow);
    }
}
