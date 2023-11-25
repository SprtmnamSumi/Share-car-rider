package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.Currency;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;
import cz.muni.fi.pv168.project.ui.model.adapters.EntityListModelAdapter;

import javax.inject.Inject;
import javax.swing.JTable;
import javax.swing.Action;

public class CarRideActionFactory implements ICarRideActionFactory {
    private final DialogFactory modalDialogFactory;
    private final EntityListModelAdapter<Currency> currencyListModel;
    private final EntityProvider entityProvider;
    private final IconLoader iconLoader;

    @Inject
    CarRideActionFactory(DialogFactory modalDialogFactory,
                         EntityListModelAdapter<Currency> currencyListModel,
                         EntityProvider entityProvider,
                         IconLoader iconLoader) {
        this.modalDialogFactory = modalDialogFactory;
        this.currencyListModel = currencyListModel;
        this.entityProvider = entityProvider;
        this.iconLoader = iconLoader;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCarRideAction(table, modalDialogFactory, entityProvider, currencyListModel, iconLoader.getIcon("add.png"));
    }

    @Override
    public Action getDeleteAction(JTable table) {
        return new DeleteCarRideAction(table, iconLoader.getIcon("bin.png"));
    }


    @Override
    public Action getEditAction(JTable table) {
        return new EditCarRideAction(table, modalDialogFactory, iconLoader.getIcon("editing.png"));
    }
}
