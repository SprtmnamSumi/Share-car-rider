package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.business.service.currenies.CurrencyConverter;
import cz.muni.fi.pv168.project.ui.dialog.TemplateDialog;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class EditTemplateAction extends AbstractAction {

    private final JTable templateTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Currency> currencyListModel;
    private final ListModel<Template> carRideTemplateListModel;
    private final CurrencyConverter currencyConverter;

    EditTemplateAction(JTable carRidesTable, ListModel<Category> categoriestListModel, EntityListModelAdapter<Currency> currencyListModel, ListModel<Template> carRideTemplateListModel, CurrencyConverter currencyConverter) {
        super("Edit", Icons.EDIT_ICON);
        this.templateTable = carRidesTable;
        this.currencyListModel = currencyListModel;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        this.currencyConverter = currencyConverter;
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
        TemplateDialog templateDialog = new TemplateDialog(template, categoriestListModel, currencyListModel, carRideTemplateListModel, currencyConverter);
        templateDialog.show(templateTable, "Edit Template")
                .ifPresent(templateListModel::updateRow);
    }
}
