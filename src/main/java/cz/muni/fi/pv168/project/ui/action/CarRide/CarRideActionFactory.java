package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.*;

public class CarRideActionFactory implements ICarRideActionFactory {
    private final DialogFactory modalDialogFactory;

    private final EntityListModelAdapter<Currency> currencyListModel;

    @Inject
    CarRideActionFactory(DialogFactory modalDialogFactory, EntityListModelAdapter<Currency> currencyListModel) {
        this.modalDialogFactory = modalDialogFactory;
        this.currencyListModel = currencyListModel;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCarRideAction(table, modalDialogFactory, currencyListModel);
    }

    @Override
    public Action getDeleteAction(JTable table) {
        return new DeleteCarRideAction(table);
    }


    @Override
    public Action getEditAction(JTable table) {
        return new EditCarRideAction(table, modalDialogFactory);
    }

}
