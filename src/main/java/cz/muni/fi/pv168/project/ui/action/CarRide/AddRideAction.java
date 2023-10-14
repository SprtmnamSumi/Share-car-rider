package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.entities.CarRideTemplate;
import cz.muni.fi.pv168.project.entities.Category;
import cz.muni.fi.pv168.project.ui.dialog.RideDialog;
import cz.muni.fi.pv168.project.ui.model.CarRide.CarRideTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddRideAction extends AbstractAction {

    private final JTable carRidesTable;
    private final TestDataGenerator testDataGenerator;
    private final ListModel<Category> categoriestListModel;
    private final ListModel<CarRideTemplate> carRideTemplateListModel;

    public AddRideAction(JTable carRidesTable, TestDataGenerator testDataGenerator, ListModel<Category> categoriestListModel, ListModel<CarRideTemplate> carRideTemplateListModel) {
        super("Add", Icons.ADD_ICON);
        this.carRidesTable = carRidesTable;
        this.testDataGenerator = testDataGenerator;
        this.categoriestListModel = categoriestListModel;
        this.carRideTemplateListModel = carRideTemplateListModel;
        putValue(SHORT_DESCRIPTION, "Adds new Ride");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var carRidesTableModel = (CarRideTableModel) carRidesTable.getModel();
        var dialog = new RideDialog(testDataGenerator.createTestRide(), categoriestListModel, carRideTemplateListModel);
        dialog.show(carRidesTable, "Add Cat ride")
                .ifPresent(carRidesTableModel::addRow);
    }
}
