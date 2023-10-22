package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.bussiness.model.CarRide;
import cz.muni.fi.pv168.project.bussiness.model.Category;
import cz.muni.fi.pv168.project.bussiness.model.Template;
import cz.muni.fi.pv168.project.ui.dialog.CarRideDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddCarRideAction extends AbstractAction {

    private final JTable carRidesTable;

    private final ListModel<Category> categoriestListModel;
    private final ListModel<Template> carRideTemplateListModel;

    public AddCarRideAction(JTable carRidesTable, ListModel<Category> categoriestListModel, ListModel<Template> carRideTemplateListModel) {
        super("Add", Icons.ADD_ICON);
        this.carRidesTable = carRidesTable;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var carRidesTableModel = (CarRideTableModel) carRidesTable.getModel();
        var dialog = new CarRideDialog(createPrefilledCarAction(), categoriestListModel, carRideTemplateListModel);
        dialog.show(carRidesTable, "Add Cat ride")
                .ifPresent(carRidesTableModel::addRow);
    }

    private CarRide createPrefilledCarAction() {
        var testDataGenerator = new TestDataGenerator();
        return testDataGenerator.createTestRide();
    }
}
