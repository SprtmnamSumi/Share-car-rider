package cz.muni.fi.pv168.project.ui.action.CarRide;

import cz.muni.fi.pv168.project.business.model.CarRide;
import cz.muni.fi.pv168.project.data.EntityProvider;
import cz.muni.fi.pv168.project.ui.action.DefaultActionFactory;
import cz.muni.fi.pv168.project.ui.dialog.DialogFactory;
import cz.muni.fi.pv168.project.ui.icons.IconLoader;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JTable;

public class CarRideActionFactory implements DefaultActionFactory<CarRide> {
    private final DialogFactory modalDialogFactory;
    private final EntityProvider entityProvider;
    private final IconLoader iconLoader;

    @Inject
    CarRideActionFactory(DialogFactory modalDialogFactory,
                         EntityProvider entityProvider,
                         IconLoader iconLoader) {
        this.modalDialogFactory = modalDialogFactory;
        this.entityProvider = entityProvider;
        this.iconLoader = iconLoader;
    }

    @Override
    public Action getAddAction(JTable table) {
        return new AddCarRideAction(table, modalDialogFactory, entityProvider, iconLoader.getIcon("add.png"));
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
