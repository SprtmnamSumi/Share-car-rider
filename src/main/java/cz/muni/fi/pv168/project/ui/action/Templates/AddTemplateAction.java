package cz.muni.fi.pv168.project.ui.action.Templates;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.business.model.Category;
import cz.muni.fi.pv168.project.business.model.Template;
import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.ui.dialog.TemplateDialog;
import cz.muni.fi.pv168.project.ui.model.Template.TemplateTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddTemplateAction extends AbstractAction {

    private final JTable templateTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Template> carRideTemplateListModel;

    public AddTemplateAction(JTable templateTable, ListModel<Category> categoriestListModel, ListModel<Template> carRideTemplateListModel) {
        super("Add", Icons.ADD_ICON);
        this.templateTable = templateTable;

        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        putValue(SHORT_DESCRIPTION, "Adds new template");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var templateTableModel = (TemplateTableModel) templateTable.getModel();
        var dialog = new TemplateDialog(createPreffiledTemplate(), categoriestListModel, carRideTemplateListModel);
        dialog.show(templateTable, "Add Cat ride")
                .ifPresent(templateTableModel::addRow);
    }

    private Template createPreffiledTemplate() {
        var testDataGenerator = new TestDataGenerator();
        return testDataGenerator.createTestTemplate();
    }
}
